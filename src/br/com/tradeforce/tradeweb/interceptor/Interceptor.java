package br.com.tradeforce.tradeweb.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWTVerifier;

import br.com.tradeforce.tradeweb.controller.UsuarioController;

public class Interceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod metodo = (HandlerMethod) handler;
		if (request.getRequestURI().contains("login")) {
			return true;
		}
		String token = request.getHeader("Authorization");
		try {
			JWTVerifier verifier = new JWTVerifier(UsuarioController.SECRET);
			Map<String, Object> claims = verifier.verify(token);
			
			if(claims.get("tipo").equals("P")){
				if (!metodo.getMethod().getName().equals("mostrarTarefa")||
					!metodo.getMethod().getName().equals("login")){
					System.out.println("Promotor não autorizado");
					response.sendError(HttpStatus.UNAUTHORIZED.value());
					return false;
				}
			}
			
//			if(!metodo.getMethod().getName().equals("mostrarTarefa")
//					&&claims.get("tipo").equals("P")){
//				
//				System.out.println("Promotor não autorizado");
//				response.sendError(HttpStatus.UNAUTHORIZED.value());
//				return false;
//			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			if (token == null) {
				response.sendError(HttpStatus.UNAUTHORIZED.value());
			} else {
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
			return false;
		}

	}
	
}
