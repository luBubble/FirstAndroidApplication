package com.task.BaseTest;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;





public class ProcessDeploy {
	ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("config/activiti.cfg.xml")
			                      .buildProcessEngine();

	public void deploymentProcessDefinition_zip(){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/mt/flow/Treat.zip");
		ZipInputStream zipInputStream = new ZipInputStream(in);
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
						.createDeployment()//创建一个部署对象
						.name("Treat02")//添加部署的名称
						.addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
						.deploy();//完成部署
		System.out.println("部署ID："+deployment.getId());//
		System.out.println("部署名称："+deployment.getName());//
	}
}
