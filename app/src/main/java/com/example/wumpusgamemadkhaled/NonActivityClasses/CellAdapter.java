package com.example.wumpusgamemadkhaled.NonActivityClasses;

import android.content.Context;
import android.os.Handler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wumpusgamemadkhaled.R;

import java.util.List;
import java.util.Random;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder>
{
	private List<Cell> cellList;
	private Context context;
	private Random random;

	public CellAdapter(List<Cell> cellList, Context context)
	{
		this.cellList = cellList;
		this.context = context;

		random = new Random();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position)
	{
		holder.view.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
			}
		});

		// for blink animation
		blinkBadSmellImageView(holder, cellList.get(position));
		blinkBreezeImageView(holder, cellList.get(position));
		blinkGoldImageView(holder, cellList.get(position));
		showUserOrWumpusOrPitImageView(holder, cellList.get(position));
	}

	@Override
	public int getItemCount()
	{
		return cellList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		public View view;

		public ImageView imageViewBadSmell;
		public ImageView imageViewBreeze;
		public ImageView imageViewGold;
		public ImageView imageViewUserOrWumpusOrPit;

		public ViewHolder(View itemView)
		{
			super(itemView);

			view = itemView.findViewById(R.id.gridItem);

			imageViewBadSmell = (ImageView) itemView.findViewById(R.id.imageViewBadSmell);
			imageViewBreeze = (ImageView) itemView.findViewById(R.id.imageViewBreeze);
			imageViewGold = (ImageView) itemView.findViewById(R.id.imageViewGold);
			imageViewUserOrWumpusOrPit = (ImageView) itemView.findViewById(R.id.imageViewUserOrWumpusOrPit);
		}
	}

	private void blinkBadSmellImageView(final ViewHolder holder, final Cell cell)
	{
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			public void run()
			{
				if( StaticComponents.isCellPresentInVisitedCellsList(cell) && cell.badSmellImagePresent == true )
				{
					holder.imageViewBadSmell.setVisibility(View.VISIBLE);
				}
				else
				{
					holder.imageViewBadSmell.setVisibility(View.INVISIBLE);
				}

				if( cell.badSmellImageState == 1 )
				{
					cell.badSmellImageState = 2;
					holder.imageViewBadSmell.setImageResource(R.drawable.bad_smell2);
				}
				else
				{
					cell.badSmellImageState = 1;
					holder.imageViewBadSmell.setImageResource(R.drawable.bad_smell1);
				}

				blinkBadSmellImageView(holder, cell);
			}
		}, (500 + random.nextInt(501)));
	}

	private void blinkBreezeImageView(final ViewHolder holder, final Cell cell)
	{
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			public void run()
			{
				if( StaticComponents.isCellPresentInVisitedCellsList(cell) && cell.breezeImagePresent == true )
				{
					holder.imageViewBreeze.setVisibility(View.VISIBLE);
				}
				else
				{
					holder.imageViewBreeze.setVisibility(View.INVISIBLE);
				}

				if( cell.breezeImageState == 1 )
				{
					cell.breezeImageState = 2;
					holder.imageViewBreeze.setImageResource(R.drawable.breeze2);
				}
				else
				{
					cell.breezeImageState = 1;
					holder.imageViewBreeze.setImageResource(R.drawable.breeze1);
				}

				blinkBreezeImageView(holder, cell);
			}
		}, (500 + random.nextInt(501)));
	}

	private void blinkGoldImageView(final ViewHolder holder, final Cell cell)
	{
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			public void run()
			{
				if( StaticComponents.isCellPresentInVisitedCellsList(cell) && cell.goldImagePresent == true )
				{
					holder.imageViewGold.setVisibility(View.VISIBLE);
				}
				else
				{
					holder.imageViewGold.setVisibility(View.INVISIBLE);
				}

				if( cell.goldImageState == 1 )
				{
					cell.goldImageState = 2;
					holder.imageViewGold.setImageResource(R.drawable.gold2);
				}
				else
				{
					cell.goldImageState = 1;
					holder.imageViewGold.setImageResource(R.drawable.gold1);
				}

				blinkGoldImageView(holder, cell);
			}
		}, (500 + random.nextInt(501)));
	}

	private void showUserOrWumpusOrPitImageView(final ViewHolder holder, final Cell cell)
	{
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			public void run()
			{
				if( StaticComponents.isCellPresentInVisitedCellsList(cell) && cell.userOrWumpusOrPitImagePresent == true )
				{
					holder.imageViewUserOrWumpusOrPit.setVisibility(View.VISIBLE);
				}
				else
				{
					holder.imageViewUserOrWumpusOrPit.setVisibility(View.INVISIBLE);
				}

				if( cell.userOrWumpusOrPitImageState == 1 )
				{
					if( cell.userImageRightDownLeftUp == 1 )
					{
						holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.user_right);
					}
					else if( cell.userImageRightDownLeftUp == 2 )
					{
						holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.user_down);
					}
					else if( cell.userImageRightDownLeftUp == 3 )
					{
						holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.user_left);
					}
					else if( cell.userImageRightDownLeftUp == 4 )
					{
						holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.user_up);
					}
				}
				else if( cell.userOrWumpusOrPitImageState == 2 )
				{
					holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.wumpus);
				}
				else if( cell.userOrWumpusOrPitImageState == 3 )
				{
					holder.imageViewUserOrWumpusOrPit.setImageResource(R.drawable.pit);
				}

				showUserOrWumpusOrPitImageView(holder, cell);
			}
		}, 100);
	}
}