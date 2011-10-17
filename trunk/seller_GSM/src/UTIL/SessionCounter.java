/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author Mr Long
 */
public class SessionCounter implements HttpSessionListener{

        private static int activeSessions = 0;

        @Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
	}

        @Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(activeSessions > 0)
			activeSessions--;
	}

        public static int getActiveSessionNumber()
        {
                return activeSessions;
        }

}
