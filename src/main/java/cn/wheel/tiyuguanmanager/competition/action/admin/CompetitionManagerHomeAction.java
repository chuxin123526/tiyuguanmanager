package cn.wheel.tiyuguanmanager.competition.action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CompetitionManagerHomeAction extends ActionSupport
{
	public String index()
	{
		return "index" ; 
	}
}
