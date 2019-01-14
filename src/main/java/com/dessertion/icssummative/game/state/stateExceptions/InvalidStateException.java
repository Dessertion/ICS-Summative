package com.dessertion.icssummative.game.state.stateExceptions;

public class InvalidStateException extends Exception{
	public InvalidStateException(int idx){
		System.err.println("Invalid state exception; state " + idx + " is invalid");
	}
}

