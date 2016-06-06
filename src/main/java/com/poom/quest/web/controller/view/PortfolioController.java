package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Portfolio;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController extends GenericViewController<Portfolio> {
	
}
