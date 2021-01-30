package com.sqli.test.models;

import com.sqli.test.enumeration.MovementType;

public class Elevator {

	private String elevatorId;
	private int currentFloor;
	private MovementType movementType;
	
	public Elevator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Elevator(String elevatorId, int currentFloor, MovementType movementType) {
		super();
		this.elevatorId = elevatorId;
		this.currentFloor = currentFloor;
		this.movementType = movementType;
	}


	public Elevator(String elevatorId, int currentFloor) {
		super();
		this.elevatorId = elevatorId;
		this.currentFloor = currentFloor;
	}


	public String getElevatorId() {
		return elevatorId;
	}


	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}


	public int getCurrentFloor() {
		return currentFloor;
	}


	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}


	public MovementType getMovementType() {
		return movementType;
	}


	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}


	@Override
	public String toString() {
		return "Elevator [elevatorId=" + elevatorId + ", currentFloor=" + currentFloor + ", movementType="
				+ movementType + "]";
	}
	
	
	
}
