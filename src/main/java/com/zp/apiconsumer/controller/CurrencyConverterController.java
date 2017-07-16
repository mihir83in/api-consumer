package com.zp.apiconsumer.controller;

import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.web.ConversionResult;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.commons.model.web.CurrencyConversionForm;
import com.zp.apiconsumer.constants.Constants;
import com.zp.apiconsumer.converter.ModelConverter;
import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.services.CurrencyConverterService;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class CurrencyConverterController {

    private CurrencyConverterService currencyConverterService;
    private ModelConverter modelConverter;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService, ModelConverter modelConverter) {
        this.currencyConverterService = currencyConverterService;
        this.modelConverter = modelConverter;
    }

    private FastDateFormat dateFormat = FastDateFormat.getInstance(Constants.DATE_PATTERN);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public CurrencyList supportedCur() {

        return currencyConverterService.getSupportedCurrencies();
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public CurrencyRates latest() {

        return currencyConverterService.getLatestRates();
    }

    @RequestMapping(value = "/historical", method = RequestMethod.GET)
    public CurrencyRates historical(@RequestParam(value = "date", required = false)
                                    @DateTimeFormat(pattern = Constants.DATE_PATTERN) Date date) {

        return currencyConverterService.getHistoricalRates(dateFormat.format(date));
    }

    @PostMapping(value = "/convert")
    public String convert(@Valid @ModelAttribute("conversionData") CurrencyConversionForm currencyConversionForm,
                          BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {

            CurrencyConvertQuery query = modelConverter.toCurrencyConvertQuery(currencyConversionForm);

            BigDecimal result = convertInternal(query.getFromCurrency(), query.getToCurrency(),
                    query.getAmount(), query.getDateEntered());
            BigDecimal rate = convertInternal(query.getFromCurrency(), query.getToCurrency(),
                    BigDecimal.valueOf(1), query.getDateEntered());

            query.setResult(result);
            query.setRate(rate);
            currencyConverterService.saveQuery(query);

            model.addAttribute("result", new ConversionResult(result, rate));
        }

        addAttributes(model);
        return "/convert";
    }

    @GetMapping(value = {"/", "/convert"})
    public String convert(@ModelAttribute("conversionData") CurrencyConversionForm form, Model model) {

        addAttributes(model);
        return "convert";
    }

    private void addAttributes(Model model) {
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("recentQueries", currencyConverterService.getLast10Queries()
                .stream().map(query -> modelConverter.toCurrencyQuery(query)).collect(Collectors.toList()));
    }

    private BigDecimal convertInternal(Currency from, Currency to, BigDecimal amount, Date date) {

        CurrencyRates latestRates = date == null ? currencyConverterService.getLatestRates(from, to)
                : currencyConverterService.getHistoricalRates(from, to, dateFormat.format(date));
        return amount.divide(latestRates.getData().get(from), 20, BigDecimal.ROUND_DOWN)
                .multiply(latestRates.getData().get(to));
    }
}
