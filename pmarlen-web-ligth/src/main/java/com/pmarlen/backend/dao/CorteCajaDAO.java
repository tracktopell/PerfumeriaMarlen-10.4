/**
 * CorteCajaDAO
 *
 * Created 2015/03/15 12:43
 *
 * @author tracktopell :: DAO Builder
 * http://www.tracktopell.com.mx
 */

package com.pmarlen.backend.dao;

import java.util.ArrayList;

import java.io.ByteArrayInputStream;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Timestamp;	

import org.apache.log4j.Logger;

import com.pmarlen.backend.model.*;
import com.tracktopell.jdbc.DataSourceFacade;
import java.util.Date;

/**
 * Class for CorteCajaDAO of Table CORTE_CAJA.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class CorteCajaDAO {

	private final static Logger logger = Logger.getLogger(CorteCajaDAO.class.getName());

	/**
	*	Datasource for table CORTE_CAJA simple CRUD operations.
	*   x common paramenter.
	*/

	private static CorteCajaDAO instance;

	private CorteCajaDAO(){	
		logger.debug("created CorteCajaDAO.");
	}

	public static CorteCajaDAO getInstance() {
		if(instance == null){
			instance = new CorteCajaDAO();
		}
		return instance;
	}

	private Connection getConnection(){
		return DataSourceFacade.getStrategy().getConnection();
	}

	private Connection getConnectionCommiteable(){
		return DataSourceFacade.getStrategy().getConnectionCommiteable();
	}

    public CorteCaja findBy(CorteCaja x) throws DAOException, EntityNotFoundException{
		CorteCaja r = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT ID,FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,TIPO_EVENTO,USUARIO_AUTORIZO FROM CORTE_CAJA "+
					"WHERE ID=?"
			);
			ps.setInt(1, x.getId());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				r = new CorteCaja();
				r.setId((Integer)rs.getObject("ID"));
				r.setFecha((Timestamp)rs.getObject("FECHA"));
				r.setSucursalId((Integer)rs.getObject("SUCURSAL_ID"));
				r.setCaja((Integer)rs.getObject("CAJA"));
				r.setUsuarioEmail((String)rs.getObject("USUARIO_EMAIL"));
				r.setSaldoInicial((Double)rs.getObject("SALDO_INICIAL"));
				r.setSaldoFinal((Double)rs.getObject("SALDO_FINAL"));
				r.setComentarios((String)rs.getObject("COMENTARIOS"));
				r.setTipoEvento((Integer)rs.getObject("TIPO_EVENTO"));
				r.setUsuarioAutorizo((String)rs.getObject("USUARIO_AUTORIZO"));
			} else {
				throw new EntityNotFoundException("CORTE_CAJA NOT FOUND FOR ID="+x.getId());
			}
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if(rs != null) {
				try{
					rs.close();
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;		
	}
	
	public CorteCaja findLastBySucursalCaja(CorteCaja x) throws DAOException{
		CorteCaja r = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT ID,FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,TIPO_EVENTO,USUARIO_AUTORIZO FROM CORTE_CAJA "+
					"WHERE SUCURSAL_ID=? AND CAJA=? ORDER BY FECHA DESC LIMIT 1"
			);
			ps.setInt(1, x.getSucursalId());
			ps.setInt(2, x.getCaja());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				r = new CorteCaja();
				r.setId((Integer)rs.getObject("ID"));
				r.setFecha((Timestamp)rs.getObject("FECHA"));
				r.setSucursalId((Integer)rs.getObject("SUCURSAL_ID"));
				r.setCaja((Integer)rs.getObject("CAJA"));
				r.setUsuarioEmail((String)rs.getObject("USUARIO_EMAIL"));
				r.setSaldoInicial((Double)rs.getObject("SALDO_INICIAL"));
				r.setSaldoFinal((Double)rs.getObject("SALDO_FINAL"));
				r.setComentarios((String)rs.getObject("COMENTARIOS"));
				r.setTipoEvento((Integer)rs.getObject("TIPO_EVENTO"));
				r.setUsuarioAutorizo((String)rs.getObject("USUARIO_AUTORIZO"));
			}
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if(rs != null) {
				try{
					rs.close();
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;		
	}
	
//    public Integer ultimoEstadoPara(CorteCaja x) throws DAOException{
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		Connection conn = null;
//		Integer tipoEvento = null;
//		try {
//			conn = getConnection();
//			StringBuilder sq = new StringBuilder("SELECT ID,FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,TIPO_EVENTO,USUARIO_AUTORIZO FROM CORTE_CAJA\n");
//			sq.append("WHERE 1=1\n");
//			sq.append("AND SUCURSAL_ID=?\n");
//			sq.append("AND CAJA=?\n");
//			sq.append("ORDER BY FECHA DESC LIMIT 1");
//			
//			ps = conn.prepareStatement(sq.toString());
//			
//			int param=1;
//			
//			ps.setInt		(param++, x.getSucursalId());
//			ps.setInt		(param++, x.getCaja());
//			
//			logger.debug("ultimoEstadoPara: Query:"+sq);
//			
//			rs = ps.executeQuery();
//			Integer id=null;
//			if(rs.next()) {
//				id         = rs.getInt("ID");
//				tipoEvento = rs.getInt("TIPO_EVENTO");
//				
//			}
//			logger.debug("ultimoEstadoPara: id="+id+", tipoEvento="+tipoEvento);
//		}catch(SQLException ex) {
//			logger.error("SQLException:", ex);
//			throw new DAOException("InQuery:" + ex.getMessage());
//		} finally {
//			if(rs != null) {
//				try{
//					rs.close();
//					ps.close();
//					conn.close();
//				}catch(SQLException ex) {
//					logger.error("ultimoEstadoPara: clossing, SQLException:" + ex.getMessage());
//					throw new DAOException("Closing:"+ex.getMessage());
//				}
//			}
//		}
//		return tipoEvento;		
//	}

	public ArrayList<CorteCaja> findAllBy(Integer sucursalId,Integer caja,Date fechaInicial,Date fechaFinal) throws DAOException {
		ArrayList<CorteCaja> r = new ArrayList<CorteCaja>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuilder sq = new StringBuilder("SELECT ID,FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,USUARIO_AUTORIZO FROM CORTE_CAJA");
			sq.append("\n");
			sq.append("WHERE 1=1\n");
			
			if(sucursalId != null){
				sq.append("AND   SUCURSAL_ID=?\n");
			}
			if(caja != null){
				sq.append("AND   CAJA=?\n");
			}
			if(fechaInicial != null){
				sq.append("AND   DATE(FECHA)>=DATE(?)\n");
			}
			if(fechaFinal != null){
				sq.append("AND   DATE(FECHA)<=DATE(?)\n");
			}
			sq.append("ORDER BY SUCURSAL_ID,CAJA,FECHA\n");
			
			ps = conn.prepareStatement(sq.toString());
			int param=1;
			if(sucursalId != null){
				ps.setObject(param++, sucursalId);
			}
			if(caja != null){
				ps.setObject(param++, caja);
			}
			if(fechaInicial != null){
				ps.setObject(param++, fechaInicial);
			}
			if(fechaFinal != null){
				ps.setObject(param++, fechaFinal);
			}
			
			rs = ps.executeQuery();
			while(rs.next()) {
				CorteCaja x = new CorteCaja();
				x.setId((Integer)rs.getObject("ID"));
				x.setFecha((Timestamp)rs.getObject("FECHA"));
				x.setSucursalId((Integer)rs.getObject("SUCURSAL_ID"));
				x.setCaja((Integer)rs.getObject("CAJA"));
				x.setUsuarioEmail((String)rs.getObject("USUARIO_EMAIL"));				
				x.setSaldoInicial((Double)rs.getObject("SALDO_INICIAL"));
				x.setSaldoFinal((Double)rs.getObject("SALDO_FINAL"));
				x.setComentarios((String)rs.getObject("COMENTARIOS"));
				x.setUsuarioAutorizo((String)rs.getObject("USUARIO_AUTORIZO"));
				r.add(x);
			}
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if(rs != null) {
				try{
					rs.close();
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;		
	};


    public ArrayList<CorteCaja> findAll() throws DAOException {
		ArrayList<CorteCaja> r = new ArrayList<CorteCaja>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT ID,FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,USUARIO_AUTORIZO,TIPO_EVENTO FROM CORTE_CAJA");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				CorteCaja x = new CorteCaja();
				x.setId((Integer)rs.getObject("ID"));
				x.setFecha((Timestamp)rs.getObject("FECHA"));
				x.setSucursalId((Integer)rs.getObject("SUCURSAL_ID"));
				x.setCaja((Integer)rs.getObject("CAJA"));
				x.setUsuarioEmail((String)rs.getObject("USUARIO_EMAIL"));				
				x.setSaldoInicial((Double)rs.getObject("SALDO_INICIAL"));
				x.setSaldoFinal((Double)rs.getObject("SALDO_FINAL"));
				x.setComentarios((String)rs.getObject("COMENTARIOS"));
				x.setUsuarioAutorizo((String)rs.getObject("USUARIO_AUTORIZO"));
				x.setTipoEvento((Integer)rs.getObject("TIPO_EVENTO"));
				r.add(x);
			}
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if(rs != null) {
				try{
					rs.close();
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;		
	};
    
    public int insert(CorteCaja x) throws DAOException {
		PreparedStatement ps = null;
		int r = -1;
		Connection conn = null;
		try {
			conn = getConnectionCommiteable();
			ps = conn.prepareStatement("INSERT INTO CORTE_CAJA(FECHA,SUCURSAL_ID,CAJA,USUARIO_EMAIL,SALDO_INICIAL,SALDO_FINAL,COMENTARIOS,USUARIO_AUTORIZO,TIPO_EVENTO) "+
					" VALUES(?,?,?,?,?,?,?,?,?)"
					,Statement.RETURN_GENERATED_KEYS);			
			int ci=1;
			
			ps.setObject(ci++,x.getFecha());
			ps.setObject(ci++,x.getSucursalId());
			ps.setObject(ci++,x.getCaja());
			ps.setObject(ci++,x.getUsuarioEmail());
			ps.setObject(ci++,x.getSaldoInicial());
			ps.setObject(ci++,x.getSaldoFinal());
			ps.setObject(ci++,x.getComentarios());
			ps.setObject(ci++,x.getUsuarioAutorizo());
			ps.setObject(ci++,x.getTipoEvento());
			
			r = ps.executeUpdate();					
			ResultSet rsk = ps.getGeneratedKeys();
			if(rsk != null){
				while(rsk.next()){
					x.setId(rsk.getInt(1));
				}
			}
			conn.commit();
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if(ps != null) {
				try{				
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;
	}

	public int update(CorteCaja x) throws DAOException {		
		PreparedStatement ps = null;
		int r= -1;
		Connection conn = null;
		try {
			conn = getConnectionCommiteable();
			ps = conn.prepareStatement("UPDATE CORTE_CAJA SET FECHA=?,SUCURSAL_ID=?,CAJA=?,USUARIO_EMAIL=?,SALDO_INICIAL=?,SALDO_FINAL=?,COMENTARIOS=?,USUARIO_AUTORIZO=?,TIPO_EVENTO=? "+
					" WHERE ID=?");
			
			int ci=1;
			
			ps.setObject(ci++,x.getFecha());
			ps.setObject(ci++,x.getSucursalId());
			ps.setObject(ci++,x.getCaja());
			ps.setObject(ci++,x.getUsuarioEmail());
			ps.setObject(ci++,x.getSaldoInicial());
			ps.setObject(ci++,x.getSaldoFinal());
			ps.setObject(ci++,x.getComentarios());
			ps.setObject(ci++,x.getUsuarioAutorizo());
			ps.setObject(ci++,x.getTipoEvento());
			
			ps.setObject(ci++,x.getId());
			
			r = ps.executeUpdate();						
			conn.commit();
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if(ps != null) {
				try{
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;
	}

    public int delete(CorteCaja x)throws DAOException {
		PreparedStatement ps = null;
		int r= -1;
		Connection conn = null;
		try {
			conn = getConnectionCommiteable();
			ps = conn.prepareStatement("DELETE FROM CORTE_CAJA WHERE ID=?");
			ps.setObject(1, x.getId());
			
			r = ps.executeUpdate();
			conn.commit();
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if(ps != null) {
				try{
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;
	}

}
