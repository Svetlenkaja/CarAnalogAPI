package by.ino.caranalogapi.aspect;

import by.ino.caranalogapi.model.CarDto;
import by.ino.caranalogapi.model.LogInfo;
import by.ino.caranalogapi.model.PaginationPage;
import by.ino.caranalogapi.repository.LogInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    final LogInfoRepository logInfoRepository;

    static final Integer SUCCESS_PROCESS = 1;
    static final Integer FAILED_PROCESS = 0;
    static final ObjectMapper mapper = new ObjectMapper();

    @AfterReturning(value = "@annotation(Logging)", returning = "result")
    @SneakyThrows
    public void cacheResult(JoinPoint joinPoint, Object result) {
        PaginationPage page = (PaginationPage) result;
        logInfoRepository.save(new LogInfo(buildRequestParameterString(joinPoint), "Найдено: " + page.totalCount() + " записей.", SUCCESS_PROCESS));
    }

    @AfterThrowing(value = "@annotation(Logging)", throwing = "exception")
    public void handleException(JoinPoint joinPoint, Throwable exception) {
        logInfoRepository.save(new LogInfo(buildRequestParameterString(joinPoint), exception.getMessage(), FAILED_PROCESS));
    }

    private String buildRequestParameterString(JoinPoint joinPoint) {
        var json = mapper.createObjectNode();

        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            Object currentArg = args[i];
            json.put(parameterNames[i], currentArg == null ? null : currentArg.toString());
        }
        return json.toString();
    }
}
