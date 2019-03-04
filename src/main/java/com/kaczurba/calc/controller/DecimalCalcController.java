package com.kaczurba.calc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaczurba.calc.bean.DecimalResultBean;
import com.kaczurba.calc.service.DecimalCalculatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * Bassic rest controller.
 * 
 * @author witol
 *
 */
@RestController
@SwaggerDefinition(info = @Info(title="DecimalCalcController", version="v1", description="simple calculator controler"))
public class DecimalCalcController {
	
	@Autowired
	DecimalCalculatorService decimalCalculatorService;
	
	// NOTE: Could use @PathVariable to aggreagete add/subtract/multiply/div methods
	//       but that means more difficulty for swagger config
	@ApiOperation(value = "Adds two double arguments")
	@GetMapping(path="/add", produces="application/json")
	public DecimalResultBean add(@RequestParam("a") double a, @RequestParam("b") double b) { 
		return new DecimalResultBean(decimalCalculatorService.add(a, b));
	}
	
	@ApiOperation(value = "Subtracts two double arguments")
	@GetMapping(path="/subtract", produces="application/json")
	public DecimalResultBean subtract(@RequestParam("a") double a, @RequestParam("b") double b) { 
		return new DecimalResultBean(decimalCalculatorService.subtract(a, b));
	}
	
	@ApiOperation(value = "Multiplies two double arguments")	
	@GetMapping(path="/multiply", produces="application/json")
	public DecimalResultBean multiply(@RequestParam("a") double a, @RequestParam("b") double b) { 
		return new DecimalResultBean(decimalCalculatorService.multiply(a, b));
	}

	@ApiOperation(value = "Divides two double arguments")	
	@GetMapping(path="/divide", produces="application/json")
	public DecimalResultBean divide(@RequestParam("a") double a, @RequestParam("b") double b) { 
		return new DecimalResultBean(decimalCalculatorService.divide(a, b));
	}
}
