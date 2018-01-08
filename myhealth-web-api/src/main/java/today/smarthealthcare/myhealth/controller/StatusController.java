package today.smarthealthcare.myhealth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
	@RequestMapping("/status")
	public String test() {
		return "OK";
	}}
