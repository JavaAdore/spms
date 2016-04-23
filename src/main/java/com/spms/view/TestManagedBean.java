
package com.spms.view;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.spms.service.TestService;


@ManagedBean
@ViewScoped
public class TestManagedBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	
	@EJB
	TestService testService;
	
	
	public void test()
	{
		testService.test();
	}
}
