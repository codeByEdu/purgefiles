package com.task.purgefiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

@SpringBootApplication
@EnableTask
public class PurgefilesApplication implements CommandLineRunner{


    public static void main(String[] args) {
        SpringApplication.run(PurgefilesApplication.class, args);
    }

    

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to ");
		PurgeTask.run();
		
	}



	@BeforeTask
	public void onTaskStartup(TaskExecution taskExecution) {
		System.out.println("Task:"+taskExecution.getTaskName()+" começou...");
		
	}



	@AfterTask
	public void onTaskEnd(TaskExecution taskExecution) {
		System.out.println("Task:"+taskExecution.getTaskName()+" terminou...");

		
	}



	@FailedTask
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		System.out.println("Task:"+taskExecution.getTaskName()+" falhou...");
		
	}
}
