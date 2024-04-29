/**
 * Copyright (c) 2013 Simon Denier
 */
package net.gecosi.internal;

import java.io.IOException;
import java.util.TooManyListenersException;

/**
 * @author Simon Denier
 * @since Mar 14, 2013
 *
 */
public interface SiPort {

	public SiMessageQueue createMessageQueue() throws TooManyListenersException, IOException;

	public CommWriter createWriter() throws IOException;

	public void setupHighSpeed();
	
	public void setupLowSpeed();

	public void close();

}
