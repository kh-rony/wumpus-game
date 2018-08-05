package com.example.wumpusgamemadkhaled.NonActivityClasses;

public class Cell
{
	public int row;
	public int column;

	public boolean badSmellImagePresent;
	public boolean breezeImagePresent;
	public boolean goldImagePresent;

	public boolean badSmellImageShow;
	public boolean breezeImageShow;
	public boolean goldImageShow;

	public boolean userOrWumpusOrPitImagePresent;
	public boolean userOrWumpusOrPitImageShow;

	public int badSmellImageState;
	public int breezeImageState;
	public int goldImageState;
	public int userOrWumpusOrPitImageState;

	public int userImageRightDownLeftUp;


	public Cell(int row, int column)
	{
		this.row = row;
		this.column = column;

		badSmellImagePresent = false;
		breezeImagePresent = false;
		goldImagePresent = false;

		badSmellImageShow = false;
		breezeImageShow = false;
		goldImageShow = false;

		userOrWumpusOrPitImagePresent = false;
		userOrWumpusOrPitImageShow = false;

		badSmellImageState = 1;
		breezeImageState = 1;
		goldImageState = 1;
		userOrWumpusOrPitImageState = 0;
		userImageRightDownLeftUp = 1;
	}
}