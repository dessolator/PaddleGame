package dev.game.project.bonuses;

public enum BonusType {
	BALL_DAMAGE,PADDLE_SPEED,PADDLE_WIDEN,MULTI_BALL,
	BALL_SPEED,PADDLE_NARROW,PADDLE_INVERT;//enum for bonuses

	/**
	 * Function used to randomize the bonuses.
	 * @return The type of bonus to be dropped.
	 */
	public static BonusType random() {
		double factor=Math.random();
		if(factor>0.86)
			return BALL_DAMAGE;
		if(factor>0.72)
			return BALL_SPEED;
		if(factor>0.58)
			return MULTI_BALL;
		if(factor>0.44)
			return PADDLE_INVERT;
		if(factor>0.30)
			return PADDLE_NARROW;
		if(factor>0.16)
			return PADDLE_SPEED;
		return PADDLE_WIDEN;
	}

}
