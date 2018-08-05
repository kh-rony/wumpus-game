package com.example.wumpusgamemadkhaled.NonActivityClasses;

import java.util.ArrayList;
import java.util.List;

public class StaticComponents
{
	public static int row = 0;
	public static int column = 0;
	public static int WIDTH = 0;

	public static Cell userCell = null;
	public static List<Cell> userAdjacentCellsList = new ArrayList();

	public static List<Cell> cellList = new ArrayList();
	public static List<Cell> visitedCellsList = new ArrayList();

	public static Cell wumpusCell = null;
	public static List<Cell> wumpusAdjacentCellsList = new ArrayList();

	public static boolean foundGold = false;
	public static boolean returnedtoStartingCell = false;

	public static boolean eatenByWumpus = false;
	public static boolean fallenIntoPit = false;

	public static boolean cheatMode = true;


	public static int changeWumpusCellDelay = 50;


	public static void resetAllVariables()
	{
		row = 0;
		column = 0;
		WIDTH = 0;

		cellList.clear();
		visitedCellsList.clear();

		userCell = null;
		userAdjacentCellsList.clear();

		wumpusCell = null;
		wumpusAdjacentCellsList.clear();

		foundGold = false;
		returnedtoStartingCell = false;

		eatenByWumpus = false;
		fallenIntoPit = false;

		//cheatMode = true;
	}

	public static void addCelltoVisitedCellsList(Cell newCell)
	{
		Cell cell;

		for( int i = 0; i < visitedCellsList.size(); i++ )
		{
			cell = (Cell) visitedCellsList.get(i);

			if( cell.row == newCell.row && cell.column == newCell.column )
			{
				return;
			}
		}

		visitedCellsList.add(newCell);
	}

	public static boolean isCellPresentInVisitedCellsList(Cell newCell)
	{
		if( cheatMode )
		{
			for( Cell cell : cellList )
			{
				if( cell.row == newCell.row && cell.column == newCell.column )
				{
					return true;
				}
			}
		}
		else
		{
			for( Cell cell : visitedCellsList )
			{
				if( cell.row == newCell.row && cell.column == newCell.column )
				{
					return true;
				}
			}
		}

		return false;
	}
}