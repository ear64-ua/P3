package model.game.score;
import model.*;

public class DestroyedFightersScore  extends Score<Fighter>{

	public DestroyedFightersScore(Side side) {
		super(side);
	}

	@Override
	public void score(Fighter sc) {
		if (sc!=null)
			super.score+=sc.getValue();
	}

}
