package org.ancloud.fw.presentation.tracking.aop;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.ancloud.fw.core.helper.DataTypeHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of service and repository Spring components.
 *
 * By default, it only runs with the "dev" profile.
 */

@Component
@Aspect
public class LoggingAspect {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final Environment env;

  public LoggingAspect(Environment env) {
    this.env = env;
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut("within(org.ancloud.repository..*) || within(org.ancloud.service..*)")
  public void loggingPointcut() {
  }

  /**
   * Advice that logs methods throwing exceptions.
   *
   * @param joinPoint
   *          join point for advice
   * @param e
   *          exception
   */
  @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    if (env.acceptsProfiles("dev")) {
      log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'",
          joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
          e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);

    } else {
      log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }
  }

  /**
   * Advice that logs when a method is entered and exited.
   *
   * @param joinPoint
   *          join point for advice
   * @return result
   * @throws Throwable
   *           throws IllegalArgumentException
   */
  @Around("loggingPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    String arguments = null;
    if (log.isTraceEnabled()) {
      if(joinPoint.getArgs()!=null) {
        arguments = Arrays.asList(joinPoint.getArgs()).stream().<String>map(arg->DataTypeHelper.toJson(arg)).collect(Collectors.joining(","));
      }
      log.trace("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName()
                          , joinPoint.getSignature().getName()
                          , arguments);
    }
    try {
      Object result = joinPoint.proceed();
      if (log.isTraceEnabled()) {
        log.trace("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName()
                          , joinPoint.getSignature().getName()
                          , DataTypeHelper.toJson(result));
      }
      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument: {} in {}.{}()", arguments
                          , joinPoint.getSignature().getDeclaringTypeName()
                          , joinPoint.getSignature().getName());
      throw e;
    }
  }
}
