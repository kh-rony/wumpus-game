package com.example.wumpusgamemadkhaled;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wumpusgamemadkhaled.NonActivityClasses.Cell;
import com.example.wumpusgamemadkhaled.NonActivityClasses.CellAdapter;
import com.example.wumpusgamemadkhaled.NonActivityClasses.StaticComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	private Cell[][] cells;

	protected List<Cell> cellList;

	private RecyclerView recyclerView;
	private CellAdapter cellAdapter;
	private GridLayoutManager gridLayoutManager;

	private Random random;

	private ImageButton imageButtonArrowRight;
	private ImageButton imageButtonArrowDown;
	private ImageButton imageButtonArrowLeft;
	private ImageButton imageButtonArrowUp;

	private Button buttonShotArrow;
	private Button buttonGrabGold;
	private Button buttonReset;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);


		buttonShotArrow = (Button) findViewById(R.id.buttonShotArrow);
		buttonGrabGold = (Button) findViewById(R.id.buttonGrabGold);
		buttonReset = (Button) findViewById(R.id.buttonReset);


		initializeCellsAndRecyclerView();


		imageButtonArrowRight = (ImageButton) findViewById(R.id.imageButtonArrowRight);
		imageButtonArrowDown = (ImageButton) findViewById(R.id.imageButtonArrowDown);
		imageButtonArrowLeft = (ImageButton) findViewById(R.id.imageButtonArrowLeft);
		imageButtonArrowUp = (ImageButton) findViewById(R.id.imageButtonArrowUp);

		imageButtonArrowRight.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( StaticComponents.userCell.userImageRightDownLeftUp != 1 )
				{
					StaticComponents.userCell.userImageRightDownLeftUp = 1;
					return;
				}

				Cell nextCell = findCell(StaticComponents.userCell.row, StaticComponents.userCell.column + 1);

				if( nextCell != null )
				{
					nextCell.userImageRightDownLeftUp = 1;

					updateUserCell(nextCell);
				}
			}
		});

		imageButtonArrowDown.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( StaticComponents.userCell.userImageRightDownLeftUp != 2 )
				{
					StaticComponents.userCell.userImageRightDownLeftUp = 2;
					return;
				}

				Cell nextCell = findCell(StaticComponents.userCell.row - 1, StaticComponents.userCell.column);

				if( nextCell != null )
				{
					nextCell.userImageRightDownLeftUp = 2;

					updateUserCell(nextCell);
				}
			}
		});

		imageButtonArrowLeft.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( StaticComponents.userCell.userImageRightDownLeftUp != 3 )
				{
					StaticComponents.userCell.userImageRightDownLeftUp = 3;
					return;
				}

				Cell nextCell = findCell(StaticComponents.userCell.row, StaticComponents.userCell.column - 1);

				if( nextCell != null )
				{
					nextCell.userImageRightDownLeftUp = 3;

					updateUserCell(nextCell);
				}
			}
		});

		imageButtonArrowUp.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( StaticComponents.userCell.userImageRightDownLeftUp != 4 )
				{
					StaticComponents.userCell.userImageRightDownLeftUp = 4;
					return;
				}

				Cell nextCell = findCell(StaticComponents.userCell.row + 1, StaticComponents.userCell.column);

				if( nextCell != null )
				{
					nextCell.userImageRightDownLeftUp = 4;

					updateUserCell(nextCell);
				}
			}
		});

		buttonShotArrow.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				buttonShotArrow.setEnabled(false);

				if( StaticComponents.userCell.userImageRightDownLeftUp == 1 )
				{
					if( StaticComponents.userCell.row == StaticComponents.wumpusCell.row && StaticComponents.userCell.column < StaticComponents.wumpusCell.column )
					{
						killWumpus();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You missed to shot arrow.\nWumpus is still alive.", Toast.LENGTH_LONG).show();
					}
				}
				else if( StaticComponents.userCell.userImageRightDownLeftUp == 2 )
				{
					if( StaticComponents.userCell.row > StaticComponents.wumpusCell.row && StaticComponents.userCell.column == StaticComponents.wumpusCell.column )
					{
						killWumpus();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You missed to shot arrow.\nWumpus is still alive.", Toast.LENGTH_LONG).show();
					}
				}
				else if( StaticComponents.userCell.userImageRightDownLeftUp == 3 )
				{
					if( StaticComponents.userCell.row == StaticComponents.wumpusCell.row && StaticComponents.userCell.column > StaticComponents.wumpusCell.column )
					{
						killWumpus();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You missed to shot arrow.\nWumpus is still alive.", Toast.LENGTH_LONG).show();
					}
				}
				else if( StaticComponents.userCell.userImageRightDownLeftUp == 4 )
				{
					if( StaticComponents.userCell.row < StaticComponents.wumpusCell.row && StaticComponents.userCell.column == StaticComponents.wumpusCell.column )
					{
						killWumpus();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You missed to shot arrow.\nWumpus is still alive.", Toast.LENGTH_LONG).show();
					}
				}
			}
		});

		buttonGrabGold.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( StaticComponents.userCell.goldImagePresent == true )
				{
					StaticComponents.foundGold = true;

					StaticComponents.userCell.goldImagePresent = false;

					Toast.makeText(getApplicationContext(), "Well done.\nYou have found gold.\nNow go back to starting cell.", Toast.LENGTH_LONG).show();
				}
			}
		});

		buttonReset.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				initializeCellsAndRecyclerView();
			}
		});


		checkGameOver();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if( id == R.id.action_settings )
		{
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void initializeCellsAndRecyclerView()
	{
		StaticComponents.resetAllVariables();

		if( StaticComponents.WIDTH == 0 )
		{
			DisplayMetrics displaymetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

			StaticComponents.WIDTH = displaymetrics.widthPixels;
		}

		if( StaticComponents.row == 0 && StaticComponents.column == 0 )
		{
			StaticComponents.row = 5;
			StaticComponents.column = 5;
		}

		random = new Random();

		cells = new Cell[StaticComponents.row][StaticComponents.column];

		for( int i = 0; i < cells.length; i++ )
		{
			for( int j = 0; j < cells[i].length; j++ )
			{
				cells[i][j] = new Cell(i, j);
			}
		}

		initializeCellsWithUserGoldWumpusPits();

		cellList = new ArrayList<Cell>();

		for( int i = (cells.length - 1); i >= 0; i-- )
		{
			for( int j = 0; j < cells[i].length; j++ )
			{
				cellList.add(cells[i][j]);
			}
		}

		StaticComponents.cellList = cellList;

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		cellAdapter = new CellAdapter(cellList, getApplicationContext());

		gridLayoutManager = new GridLayoutManager(getApplicationContext(), StaticComponents.column);

		recyclerView.setLayoutManager(gridLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(cellAdapter);

		buttonShotArrow.setEnabled(true);
	}

	private void initializeCellsWithUserGoldWumpusPits()
	{
		//user cell

		cells[0][0].userOrWumpusOrPitImagePresent = true;
		cells[0][0].userOrWumpusOrPitImageShow = true;
		cells[0][0].userOrWumpusOrPitImageState = 1;// 1 for user

		StaticComponents.userCell = cells[0][0];
		StaticComponents.addCelltoVisitedCellsList(cells[0][0]);

		StaticComponents.userAdjacentCellsList.add(cells[0][1]);
		StaticComponents.userAdjacentCellsList.add(cells[1][0]);

		int goldRow;
		int goldColumn;

		//gold cell
		while( true )
		{
			goldRow = random.nextInt(StaticComponents.row);
			goldColumn = random.nextInt(StaticComponents.column);

			if( cells[goldRow][goldColumn].userOrWumpusOrPitImagePresent == false && cells[goldRow][goldColumn].goldImagePresent == false )
			{
				cells[goldRow][goldColumn].goldImagePresent = true;
				cells[goldRow][goldColumn].goldImageShow = true;

				break;
			}
		}

		int wumpusRow;
		int wumpusColumn;

		//wumpus cell
		while( true )
		{
			wumpusRow = random.nextInt(StaticComponents.row);
			wumpusColumn = random.nextInt(StaticComponents.column);

			if( cells[wumpusRow][wumpusColumn].userOrWumpusOrPitImagePresent == false && cells[wumpusRow][wumpusColumn].goldImagePresent == false )
			{
				StaticComponents.wumpusCell = cells[wumpusRow][wumpusColumn];

				cells[wumpusRow][wumpusColumn].userOrWumpusOrPitImagePresent = true;
				cells[wumpusRow][wumpusColumn].userOrWumpusOrPitImageShow = true;
				cells[wumpusRow][wumpusColumn].userOrWumpusOrPitImageState = 2;// 2 for wumpus

				cells[wumpusRow][wumpusColumn].badSmellImagePresent = true;
				cells[wumpusRow][wumpusColumn].badSmellImageShow = true;

				if( (wumpusColumn + 1) <= (StaticComponents.column - 1) )
				{
					StaticComponents.wumpusAdjacentCellsList.add(cells[wumpusRow][wumpusColumn + 1]);

					cells[wumpusRow][wumpusColumn + 1].badSmellImagePresent = true;
					cells[wumpusRow][wumpusColumn + 1].badSmellImageShow = true;
				}
				if( (wumpusRow - 1) >= 0 )
				{
					StaticComponents.wumpusAdjacentCellsList.add(cells[wumpusRow - 1][wumpusColumn]);

					cells[wumpusRow - 1][wumpusColumn].badSmellImagePresent = true;
					cells[wumpusRow - 1][wumpusColumn].badSmellImageShow = true;
				}
				if( (wumpusColumn - 1) >= 0 )
				{
					StaticComponents.wumpusAdjacentCellsList.add(cells[wumpusRow][wumpusColumn - 1]);

					cells[wumpusRow][wumpusColumn - 1].badSmellImagePresent = true;
					cells[wumpusRow][wumpusColumn - 1].badSmellImageShow = true;
				}
				if( (wumpusRow + 1) <= (StaticComponents.row - 1) )
				{
					StaticComponents.wumpusAdjacentCellsList.add(cells[wumpusRow + 1][wumpusColumn]);

					cells[wumpusRow + 1][wumpusColumn].badSmellImagePresent = true;
					cells[wumpusRow + 1][wumpusColumn].badSmellImageShow = true;
				}

				break;
			}
		}

		int pitRow;
		int pitColumn;

		int numberOfPit = (int) (((StaticComponents.row * StaticComponents.column) * 20) / 100);

		//setting cells of pits
		while( numberOfPit > 0 )
		{
			pitRow = random.nextInt(StaticComponents.row);
			pitColumn = random.nextInt(StaticComponents.column);

			if( cells[pitRow][pitColumn].userOrWumpusOrPitImagePresent == false && cells[wumpusRow][wumpusColumn].goldImagePresent == false )
			{
				cells[pitRow][pitColumn].userOrWumpusOrPitImagePresent = true;
				cells[pitRow][pitColumn].userOrWumpusOrPitImageShow = true;
				cells[pitRow][pitColumn].userOrWumpusOrPitImageState = 3;//3 for pit

				if( (pitColumn + 1) <= (StaticComponents.column - 1) )
				{
					cells[pitRow][pitColumn + 1].breezeImagePresent = true;
					cells[pitRow][pitColumn + 1].breezeImageShow = true;
				}
				if( (pitRow - 1) >= 0 )
				{
					cells[pitRow - 1][pitColumn].breezeImagePresent = true;
					cells[pitRow - 1][pitColumn].breezeImageShow = true;
				}
				if( (pitColumn - 1) >= 0 )
				{
					cells[pitRow][pitColumn - 1].breezeImagePresent = true;
					cells[pitRow][pitColumn - 1].breezeImageShow = true;
				}
				if( (pitRow + 1) <= (StaticComponents.row - 1) )
				{
					cells[pitRow + 1][pitColumn].breezeImagePresent = true;
					cells[pitRow + 1][pitColumn].breezeImageShow = true;
				}

				numberOfPit--;
			}
		}
	}

	private Cell findCell(int row, int column)
	{
		for( Cell cell : cellList )
		{
			if( cell.row == row && cell.column == column )
			{
				return cell;
			}
		}

		return null;
	}

	private Cell findNewCellForWumpus(int row, int column)
	{
		for( Cell cell : cellList )
		{
			if( cell.row == row && cell.column == column )
			{
				if( cell.userOrWumpusOrPitImagePresent == false )
				{
					return cell;
				}
			}
		}

		return null;
	}

	private void updateUserCell(Cell nextCell)
	{
		if( nextCell.userOrWumpusOrPitImagePresent == true )
		{
			if( nextCell.userOrWumpusOrPitImageState == 2 )
			{
				StaticComponents.eatenByWumpus = true;
			}
			else if( nextCell.userOrWumpusOrPitImageState == 3 )
			{
				StaticComponents.fallenIntoPit = true;
			}
			return;
		}

		StaticComponents.userCell.userOrWumpusOrPitImagePresent = false;
		StaticComponents.userCell.userOrWumpusOrPitImageShow = false;
		StaticComponents.userCell.userOrWumpusOrPitImageState = 0;

		nextCell.userOrWumpusOrPitImagePresent = true;
		nextCell.userOrWumpusOrPitImageShow = true;
		nextCell.userOrWumpusOrPitImageState = 1;

		StaticComponents.userCell = nextCell;
		StaticComponents.addCelltoVisitedCellsList(nextCell);

		StaticComponents.userAdjacentCellsList.clear();

		if( findCell(nextCell.row, nextCell.column + 1) != null )
		{
			StaticComponents.userAdjacentCellsList.add(findCell(nextCell.row, nextCell.column + 1));
		}
		if( findCell(nextCell.row - 1, nextCell.column) != null )
		{
			StaticComponents.userAdjacentCellsList.add(findCell(nextCell.row - 1, nextCell.column));
		}
		if( findCell(nextCell.row, nextCell.column - 1) != null )
		{
			StaticComponents.userAdjacentCellsList.add(findCell(nextCell.row, nextCell.column - 1));
		}
		if( findCell(nextCell.row + 1, nextCell.column) != null )
		{
			StaticComponents.userAdjacentCellsList.add(findCell(nextCell.row + 1, nextCell.column));
		}
	}

	private void killWumpus()
	{
		StaticComponents.wumpusCell.userOrWumpusOrPitImagePresent = false;
		StaticComponents.wumpusCell.userOrWumpusOrPitImageShow = false;

		StaticComponents.wumpusCell.badSmellImagePresent = false;
		StaticComponents.wumpusCell.badSmellImageShow = false;

		Cell cell;

		for( int i = 0; i < StaticComponents.wumpusAdjacentCellsList.size(); i++ )
		{
			cell = (Cell) StaticComponents.wumpusAdjacentCellsList.get(i);

			cell.badSmellImagePresent = false;
			cell.badSmellImageShow = false;
		}

		StaticComponents.wumpusAdjacentCellsList.clear();

		Toast.makeText(getApplicationContext(), "You have killed wumpus.", Toast.LENGTH_LONG).show();
	}

	private void checkGameOver()
	{
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			public void run()
			{
				if( StaticComponents.userCell.row == 0 && StaticComponents.userCell.column == 0 && StaticComponents.foundGold == true )
				{
					StaticComponents.returnedtoStartingCell = true;
				}

				StaticComponents.changeWumpusCellDelay--;

				if( StaticComponents.changeWumpusCellDelay <= 0 && StaticComponents.wumpusCell.userOrWumpusOrPitImagePresent == true )
				{
					StaticComponents.changeWumpusCellDelay = (50 + random.nextInt(51));

					Cell newWumpusCell;
					List<Cell> newWumpusAdjacentCellsList = new ArrayList<>();

					newWumpusCell = findNewCellForWumpus(StaticComponents.wumpusCell.row, StaticComponents.wumpusCell.column + 1);
					if( newWumpusCell != null )
					{
						newWumpusAdjacentCellsList.add(newWumpusCell);
					}

					newWumpusCell = findNewCellForWumpus(StaticComponents.wumpusCell.row - 1, StaticComponents.wumpusCell.column);
					if( newWumpusCell != null )
					{
						newWumpusAdjacentCellsList.add(newWumpusCell);
					}

					newWumpusCell = findNewCellForWumpus(StaticComponents.wumpusCell.row, StaticComponents.wumpusCell.column - 1);
					if( newWumpusCell != null )
					{
						newWumpusAdjacentCellsList.add(newWumpusCell);
					}

					newWumpusCell = findNewCellForWumpus(StaticComponents.wumpusCell.row + 1, StaticComponents.wumpusCell.column);
					if( newWumpusCell != null )
					{
						newWumpusAdjacentCellsList.add(newWumpusCell);
					}

					if( newWumpusAdjacentCellsList.isEmpty() == false )
					{
						//Toast.makeText(getApplicationContext(), "Changing Wumpus Cell", Toast.LENGTH_SHORT).show();

						newWumpusCell = newWumpusAdjacentCellsList.get(0);

						newWumpusAdjacentCellsList.clear();

						Cell temp;

						temp = findCell(newWumpusCell.row, newWumpusCell.column + 1);
						if( temp != null )
						{
							newWumpusAdjacentCellsList.add(temp);
						}

						temp = findCell(newWumpusCell.row - 1, newWumpusCell.column);
						if( temp != null )
						{
							newWumpusAdjacentCellsList.add(temp);
						}

						temp = findCell(newWumpusCell.row, newWumpusCell.column - 1);
						if( temp != null )
						{
							newWumpusAdjacentCellsList.add(temp);
						}

						temp = findCell(newWumpusCell.row + 1, newWumpusCell.column);
						if( temp != null )
						{
							newWumpusAdjacentCellsList.add(temp);
						}


						StaticComponents.wumpusCell.userOrWumpusOrPitImagePresent = false;
						StaticComponents.wumpusCell.userOrWumpusOrPitImageShow = false;

						StaticComponents.wumpusCell.badSmellImagePresent = false;
						StaticComponents.wumpusCell.badSmellImageShow = false;


						for( int i = 0; i < StaticComponents.wumpusAdjacentCellsList.size(); i++ )
						{
							temp = StaticComponents.wumpusAdjacentCellsList.get(i);

							temp.badSmellImagePresent = false;
							temp.badSmellImageShow = false;
						}

						newWumpusCell.userOrWumpusOrPitImagePresent = true;
						newWumpusCell.userOrWumpusOrPitImageShow = true;
						newWumpusCell.userOrWumpusOrPitImageState = 2;

						newWumpusCell.badSmellImagePresent = true;
						newWumpusCell.badSmellImageShow = true;

						StaticComponents.wumpusCell = newWumpusCell;

						for( int i = 0; i < newWumpusAdjacentCellsList.size(); i++ )
						{
							temp = newWumpusAdjacentCellsList.get(i);

							temp.badSmellImagePresent = true;
							temp.badSmellImageShow = true;
						}

						StaticComponents.wumpusAdjacentCellsList.clear();
						StaticComponents.wumpusAdjacentCellsList = newWumpusAdjacentCellsList;
					}
				}


				if( StaticComponents.foundGold == true && StaticComponents.returnedtoStartingCell == true )
				{
					Toast.makeText(getApplicationContext(), "Congratulations, you have won.", Toast.LENGTH_LONG).show();
					initializeCellsAndRecyclerView();
				}
				else if( StaticComponents.eatenByWumpus == true )
				{
					Toast.makeText(getApplicationContext(), "You loose\nEaten by Wumpus", Toast.LENGTH_LONG).show();
					initializeCellsAndRecyclerView();
				}
				else if( StaticComponents.fallenIntoPit == true )
				{
					Toast.makeText(getApplicationContext(), "You loose\nFallen into Pit", Toast.LENGTH_LONG).show();
					initializeCellsAndRecyclerView();
				}

				checkGameOver();
			}
		}, 100);
	}
}