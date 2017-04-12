package net.bechtelus.CTA;


import java.util.List;

import net.bechtelus.common.DAOException;
import net.bechtelus.user.User;
import net.bechtelus.CTA.CallToAction;;

/**
 * @author daniel
 *
 */
public interface CallToActionDAO  {
	

	public boolean insertCTA(CallToAction cta) throws DAOException;
	
	
	public void updateCTA(CallToAction cta) throws DAOException;
	
	
	public void deleteCTA(long id) throws DAOException;

	public CallToAction getCallToActionById(long id) throws DAOException;

	
	public List<CallToAction> getCallToActions(String userId, boolean top, int maxTop, int maxDefault) throws DAOException;

	public List<CallToAction> getAllCallToActions(String userId) throws DAOException;
	
	
}
