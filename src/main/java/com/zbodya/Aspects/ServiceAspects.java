package com.zbodya.Aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspects 
{

	private Logger LOG = Logger.getLogger(getClass().getName());
	

	
//	@Around("within(com.zbodya.Service.*)")
//	public void measureMethodExecutionTime(ProceedingJoinPoint pjp) 
//	{
//		long start = System.nanoTime();
//        try 
//        {
//			pjp.proceed();
//		} catch (Throwable e) 
//        {			
//			e.printStackTrace();
//		}
//        long end = System.nanoTime();
//        String methodName = pjp.getSignature().getName();
//        LOG.info("Execution of " + methodName + " took " + (end - start)/1000 + " ms");
//	}
	
}
