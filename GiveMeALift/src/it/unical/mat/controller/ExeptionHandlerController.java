//package it.unical.mat.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.portlet.ModelAndView;
//
//@ControllerAdvice
//class ExceptionHandlerController {
//    public static final String DEFAULT_ERROR_VIEW = "error";
//
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//    	System.out.println("***********************************************");
//        // If the exception is annotated with @ResponseStatus rethrow it and let
//        // the framework handle it - like the OrderNotFoundException example
//        // at the start of this post.
//        // AnnotationUtils is a Spring Framework utility class.
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
//            throw e;
//
//        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
//}
