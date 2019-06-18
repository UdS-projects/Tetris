package tetris.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tetris.game.MyTetrisFactory;

/**
 * Within this class and/or package you can implement your own tests that will
 * be run with the reference implementation.
 *
 * Note that no classes or interfaces will be available, except those initially
 * provided. Make use of {@link MyTetrisFactory} to get other factory instances.
 */
public class MyTetrisTest {

	@Test
	public void test() {
		assertNotNull(MyTetrisFactory.createBoard(MyTetrisFactory.DEFAULT_ROWS, MyTetrisFactory.DEFAULT_COLUMNS));
	}

}
