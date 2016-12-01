package br.com.tradeforce.tradeweb.controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTSigner;

import br.com.tradeforce.tradeweb.dao.UsuarioDao;
import br.com.tradeforce.tradeweb.model.Usuario;


@RestController
public class UsuarioController {
	public static final String SECRET = "tradeforce";
	public static final String ISSUER = "Trade Force";
	
	@Autowired
	private UsuarioDao UsuDao;
	
	@RequestMapping(value="/login",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> logar(@RequestBody Usuario usuario){
		try {
			usuario = UsuDao.logar(usuario);
			if(usuario != null){
				//data de emissao do token
				long iat = System.currentTimeMillis() / 1000;
				//data de expira��o do token
				long exp = iat + 60;
				// objeto que ira gerar o token
				JWTSigner signer = new JWTSigner(SECRET);
				HashMap<String, Object> claims = new HashMap<>();
				claims.put("iat", iat);
				claims.put("exp", exp);
				claims.put("iss", ISSUER);
				claims.put("id_usuario", usuario.getId());
				// gerar o token
				String jwt = signer.sign(claims);
				JSONObject token = new JSONObject();
				token.put("token", jwt);
				return ResponseEntity.ok(token.toString());
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
