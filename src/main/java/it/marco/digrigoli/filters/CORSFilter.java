package it.marco.digrigoli.filters;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Context
	private ResourceInfo resourceInfo;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		logger.info("Adding CORS "+requestContext.getHeaders()+":"+requestContext.getMethod()+"...");
		
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "*, Origin, X-Requested-With, Content-Type, Accept");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, HEAD");
		responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location,Authorization");
		
		logger.info("CORS added.");
	}	

}
