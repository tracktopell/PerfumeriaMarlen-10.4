package com.pmarlen.jsf;

import com.pmarlen.model.GeneradorDeToken;
import java.io.Serializable;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="generadorTokenMB")
@ViewScoped
public class GeneradorTokenMB  implements Serializable {
	
	private String frase;
	
	private String token;
	
	private transient final Logger logger = Logger.getLogger(GeneradorTokenMB.class.getSimpleName());
	
	@PostConstruct
    public void init() {
		logger.trace("init.");		
    }

	/**
	 * @return the frase
	 */
	public String getFrase() {
		return frase;
	}

	/**
	 * @param frase the frase to set
	 */
	public void setFrase(String frase) {
		this.frase = frase;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		if(frase != null) {
			GeneradorDeToken gt= new GeneradorDeToken();
			token = gt.getToken(frase);
		}
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public void generarToken(){
		logger.trace("generarToken");		
	}
	

}
