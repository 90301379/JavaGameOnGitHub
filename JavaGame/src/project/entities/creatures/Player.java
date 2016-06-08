package project.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import project.entities.Entity;
import project.entities.creatures.player.internal.Ability;
import project.entities.creatures.player.internal.Stats;
import project.gfx.Animation;
import project.gfx.Assets;
import project.tileGame.Handler;
import project.tiles.Tile;

public class Player extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	public boolean alive = true;

	// Ability and Hud Code:
	private Ability q = new Ability("This is a q", Assets.player_down[0]);
	private Ability w = new Ability("This is a w", Assets.player_down[0]);
	private Ability e = new Ability("This is an e", Assets.player_down[0]);
	private Ability r = new Ability("This is an R", Assets.player_down[0]);

	public Stats stat = new Stats();
	private boolean moving = false;
	private int desX = 100;
	private int desY = 100;
	
	public boolean canTakeDMG = true;
	public int setTime = 0;
	public boolean available = false; 
	
	public boolean sprinting = false; 
	public int sprintCharge = 3; 

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;

		// Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);


		stat.health = 200;
	
	}

	@Override
	public void tick() {
		
		// Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		// Movement
		getInput();
		//playerMove(desX, desY);
		move();
		if (this.stat.health <= 0) {
			this.stat.health = 0;
		}
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		
		if(sprinting){
			speed = 4.269F;
			if(this.sprintCharge == 0){
				speed = 2.0F;
				sprinting = false;
			}
		}else{
			speed = 3.0F;
		}
		
	}


	private void playerMove(int desX2, int desY2) {

		xMove = 0;
		yMove = 0;

		if (moving) {

			// getClosestPoint(P);
			// Above Below
			if ((((this.getY() < desY - 10) || (this.getY() > desY + 10))
					// Left Right
					|| ((this.getX() < desX - 10) || (this.getX() > desX + 10)))) {

				// Up
				if (this.getY() > desY) {
					yMove = -speed;
					// System.out.println("Up");
				}
				// Down
				if (this.getY() < desY) {
					yMove = speed;
					// System.out.println("Down");
				}
				// Right
				if (this.getX() > desX) {
					xMove = -speed;
					// System.out.println("Left");
				}
				// Left
				if (this.getX() < desX) {
					xMove = speed;
					// System.out.println("Right");
				}
			} else {

				moving = false;

			}
		}
				// getClosestPoint(P);
				// Above Below
				if ((((this.getY() < desY) || (this.getY() > desY))
						// Left Right
						|| ((this.getX() < desX) || (this.getX() > desX)))) {

					// Up
					if (this.getY() > desY) {
						yMove = -speed;
						// System.out.println("Up");
					}
					// Down
					if (this.getY() < desY) {
						yMove = speed;
						// System.out.println("Down");
					}
					// Right
					if (this.getX() > desX) {
						xMove = -speed;
						// System.out.println("Left");
					}
					// Left
					if (this.getX() < desX) {
						xMove = speed;
						// System.out.println("Right");
					}
				} else {

					moving = false;

				}
			}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}

		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}

	}

	@Override
	public void die() {
		System.out.println("You lose");
	}

	public boolean collidesWith(int x, int y) {

		if ((int) this.getX() == x && (int) this.getY() == y) {
			System.out.println("Colides with x ansdasdadd y | " + x + "," + y);
			System.out.println("Colides with x and y | " + this.getX() + "," + this.getY());
			return true;
		}
		return false;

	}

	public boolean fireRight = false;
	public boolean fireUp = false;
	public boolean fireLeft = false;
	public boolean fireDown = false;

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right){
			xMove = speed;
		}
		if (handler.getKeyManager().space){
			sprinting = true;
		}else{
			sprinting = false;
		}
		
		
		if (handler.getKeyManager().aRight){
			fireRight = true;
		}else{
			fireRight = false;
		}
		
		if (handler.getKeyManager().aLeft){
			fireLeft = true;
		}else{
			fireLeft = false;
		}
		
		if (handler.getKeyManager().aUp){
			fireUp = true;
		}else{
			fireUp = false;
		}
		
		if (handler.getKeyManager().aDown){
			fireDown = true;
		}else{
			fireDown = false;
		}
//		if (handler.getMouseManager().onePress()) {
//
//			desX = handler.getMouseManager().getMouseX() + (int) Math.max(0, handler.getGameCamera().getxOffset());
//			desY = handler.getMouseManager().getMouseY() + (int) Math.max(0, handler.getGameCamera().getyOffset());
//
//			//
//			// desX = (int) (handler.getMouseManager().getMouseX() +
//			// this.getX());
//			// desY = (int) (handler.getMouseManager().getMouseY() +
//			// this.getY());
//			System.out.println("X: " + desX);
//			System.out.println(
//					"Please Kill Me: " + (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH));
//
//			desX = handler.getMouseManager().getMouseX();
//			desY = handler.getMouseManager().getMouseY();
//			System.out.println("X: " + desX);
//			System.out.println("Y: " + desY);
//			moving = true;
//			handler.getMouseManager().one = false;
//		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// g.setColor(Color.red);
		// g.fillRect((int) (x + bounds.x -
		// handler.getGameCamera().getxOffset()),
		// (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		// bounds.width, bounds.height);

	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

	public void damage(int i) {
		this.stat.health = this.stat.health - i;
		available = true;
		canTakeDMG = false;
	}

}
