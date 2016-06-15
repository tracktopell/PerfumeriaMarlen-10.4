package com.pmarlen.caja.model;

/**
 *
 * @author alfredo
 */
public interface SyncUpdateListener {
	void updateSyncInfo();
	void resetAll();
	void updateProhibidaVentOpo(boolean estatus);
	void updateProhibidaVentReg(boolean estatus);
}
