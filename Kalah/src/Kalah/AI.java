    /**
	Java Class for AI
	**/
	
class AI {
	public: 
		/*Public Data Structures*/
		const MODE {Human, AI};
		const DIFFICULTY { Easy, Medium, Hard }; 
		
	private: 
	
		/*Private Data Structures*/
		MODE mode;
		DIFFICULTY difficulty; 
		bool isWhite; //Color of player 
		
		/*Private Member Functions for Difficulty*/
		vector<int> calc_ranrom(Board board); //Returns random move, EASY AI 
		vector<int> minmax(Board board); //Returns a move determined using minimax
		vector<int> calc_ab_pruning(Board board); //returns move determines using alpha beta pruning 
		
		int minmax(Board board, int depth, int alpha, int beta, bool, int, int);
		
	public: 
	
	/*Constructors*/
	AI();
	AI(MODE md, DIFFICULTY df, bool iswhite);
	
	/*PUBLIC MEMBER FUNCTIONS*/
	
	vector<int>get_move(Board& board); //returns a vector containing x and y of AI's desired move
		
}


