package ra.edu.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MovieAspect {
    @Before("execution(* ra.edu.service.impl.MovieServiceImpl.createMovie(..))" + " || execution(* ra.edu.service.impl.MovieServiceImpl.updateMovie(..))")
    public void logMethod(JoinPoint joinPoint) {
        log.info("Method được gọi: {}", joinPoint.getSignature().getName());
    }
}