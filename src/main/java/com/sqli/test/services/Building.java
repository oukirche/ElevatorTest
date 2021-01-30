package com.sqli.test.services;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.sqli.test.enumeration.MovementType;
import com.sqli.test.models.Elevator;

public class Building {
	
	private List<Elevator> elevators;
	private int numberOfFloors;
	private Elevator globalElevator;
	
	
//  public void the_closest_elevator_to_floor_zero_should_arrive_first() {
//  int numberOfFloors = 10;
//  Building building = new Building(numberOfFloors, "id1:1", "id2:6");// "[elevator_id]:[elevator_current_floor]"
//
//  String idOfFirstAvailableElevator = building.requestElevator(); // a request is by default from floor 0.
//
//  assertEquals("id1", idOfFirstAvailableElevator);
//}
	public Building(int numberOfFloors,String... elevatorsStringForm) {
		super();
		this.numberOfFloors = numberOfFloors;
		elevators = new Vector<Elevator>();
		buildElevatorsVectors(elevatorsStringForm);
		globalElevator = new Elevator();
	}

	private void buildElevatorsVectors(String[] elevatorsStringForm) {
		
		for(String s:elevatorsStringForm)
		{
			String[] elevatorString = s.split(":");
			Elevator elevator = new Elevator(elevatorString[0], Integer.parseInt(elevatorString[1]),MovementType.RESTING);
			elevators.add(elevator);
		}
		
	}
	public String requestElevator()
	{
		Elevator tmpElevator = new Elevator();
		tmpElevator = getNearElevator();
	
		
		return tmpElevator.getElevatorId();
	}
	
	private Elevator getNearElevator()
	{
		Elevator tmpElevator = new Elevator();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(Elevator v : elevators)
		{
			if("RESTING".equals(v.getMovementType().toString()))
			{
				return v;
			}
			if("UP".equals(v.getMovementType().toString()) && v.getCurrentFloor()>max )
			{
				
				max=v.getCurrentFloor();
				System.out.println(max);
				tmpElevator = v;
			}
			if("DOWN".equals(v.getMovementType().toString()) && v.getCurrentFloor()<max )
			{
				max=v.getCurrentFloor();
				tmpElevator = v;
			}
			if(v.getCurrentFloor()<min && v.getMovementType()==null)
				{
					min = v.getCurrentFloor();
					tmpElevator = v;
				}
			
			
		}
		
		return tmpElevator;
	}

	
	
//  public void elevators_going_up_arrive_last_to_floor_zero() {
//  Building building = new Building(10, "id1:1", "id2:6");
//  building.move("id1", "UP"); // tell the elevator "id1" to move "UP".
//
//  String idOfFirstAvailableElevator = building.requestElevator();
//
//  assertEquals("id2", idOfFirstAvailableElevator);
//}
	
	public void move(String elevatorId,String movmentType)
	{
	
		Elevator tmpElevator = getElevatorByID(elevatorId);
		tmpElevator.setElevatorId(elevatorId);
		//tmpElevator.setMovementType(movementType);
		if(tmpElevator!=null)
		{
			
			if("UP".equals(movmentType.toString()) && tmpElevator.getCurrentFloor()<=numberOfFloors)
			{
				tmpElevator.setCurrentFloor(tmpElevator.getCurrentFloor()+1);
				tmpElevator.setMovementType(MovementType.UP);
			}
			else if("DOWN".equals(movmentType.toString()))
				{
					tmpElevator.setCurrentFloor(0);
					tmpElevator.setMovementType(MovementType.DOWN);
				}
			
			elevators.remove(tmpElevator);
			elevators.add(tmpElevator);
		}
		
		
	}
	
	public Elevator getElevatorByID(String elevatorId)
	{
		for(Elevator v : elevators)
		{
			if(elevatorId.equals(v.getElevatorId()))
				{
					return v;
				}
		}
		return null;
	}
	

	
//  @Test
//  public void can_request_elevator_in_middle_of_building() {
//      Building building = new Building(10, "id1:1", "id2:6");
//
//      String idOfFirstAvailableElevator = building.requestElevator(5); // the request is made at the 5th floor
//
//      assertEquals("id2", idOfFirstAvailableElevator); // "id2" is the closest elevator to 5th floor
//  }

	public String requestElevator(int floorNumber)
	{
		Elevator tmpElevator = new Elevator();
		tmpElevator = getNearElevator(floorNumber);
		return tmpElevator.getElevatorId();
		
	}
	private Elevator getNearElevator(int floorNumber)
	{
		Elevator tmpElevator = new Elevator();
		int min = Integer.MAX_VALUE;
		for(Elevator v : elevators)
		{
			int difference = Math.abs(v.getCurrentFloor()-floorNumber);
			
			if(difference<min && v.getMovementType()==MovementType.RESTING)
				{
					
					min = Math.abs(v.getCurrentFloor()-floorNumber);
					tmpElevator = v;
				}
			
		}
		return tmpElevator;
	}

	
	

}
