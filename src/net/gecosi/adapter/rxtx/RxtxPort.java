/**
 * Copyright (c) 2013 Simon Denier
 */
package net.gecosi.adapter.rxtx;

import java.io.IOException;
import java.util.TooManyListenersException;

import com.fazecast.jSerialComm.SerialPort;

import net.gecosi.internal.CommWriter;
import net.gecosi.internal.SiMessageQueue;
import net.gecosi.internal.SiPort;

/**
 * @author Simon Denier
 * @since Mar 10, 2013
 *
 */
public class RxtxPort implements SiPort {

	private SerialPort port;

	public RxtxPort(SerialPort port) {
		this.port = port;
	}
	
	public SerialPort getPort() {
		return port;
	}
	
	public SiMessageQueue createMessageQueue() throws TooManyListenersException, IOException {
		SiMessageQueue messageQueue = new SiMessageQueue(10);
		port.addDataListener(new RxtxCommReader(port.getInputStream(), messageQueue));
		//port.notifyOnDataAvailable(true);
		return messageQueue;
	}
	
	public CommWriter createWriter() throws IOException {
		return new RxtxCommWriter(port.getOutputStream());
	}

	public void setupHighSpeed() {
		setSpeed(38400);		
	}

	public void setupLowSpeed() {
		setSpeed(4800);		
	}

	public void setSpeed(int baudRate) {
		port.setComPortParameters(baudRate, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
		
	}

	public void close() {
		// TODO: close streams?
		port.closePort();
	}
	
}
