package Kalah;

import java.util.Random;

/**
	 Easy AI
	**/

public void EasyMove(int num)
	{
		int num = num;
		if (getSeedsinHouse(randSelect) != 0)
		{
			moveSeeds(chosen player, randSelect);
		}
		else
		{
			EasyMove();
		}
	}

public class AIEasy
{
	int numHouses = getHousesPerPlayer();  //get total number of houses on board per side
	vector<int> pickHouse[];
	for (int i = 0; i < numHouses; i++)       //fill a vector with number 1-total num of houses, to be used in the random selection
	{
		numHouses.pushback(i+1);
	}
	int randSelect = random.nextInt(pickHouse.size() - pickHouse[0] + 1) + pickHouse[0];
	EasyMove(randSelect);	
}

