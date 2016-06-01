package cn.wheel.tiyuguanmanager.competition.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CompetitionHomeAction extends ActionSupport
{	
	public String index()
	{
		return "index" ; 
	}

}
