package org.sirix.xquery.node;

import java.io.FileNotFoundException;
import org.brackit.xquery.QueryException;
import org.brackit.xquery.XMarkTest;
import org.brackit.xquery.XQuery;
import org.brackit.xquery.node.parser.DocumentParser;
import org.brackit.xquery.xdm.Collection;
import org.brackit.xquery.xdm.DocumentException;
import org.brackit.xquery.xdm.Store;
import org.junit.After;
import org.sirix.xquery.SirixCompileChain;

/**
 * XMark test.
 *
 * @author Johannes Lichtenberger
 *
 */
public final class SirixXMarkTest extends XMarkTest {

  /** Sirix database store. */
  private BasicDBStore mStore;

  @Override
  protected Store createStore() throws Exception {
    mStore = BasicDBStore.newBuilder().build();
    return mStore;
  }

  @Override
  protected XQuery xquery(final String query) throws QueryException {
    return new XQuery(new SirixCompileChain(mStore), query);
  }

  @Override
  protected Collection<?> createDoc(final DocumentParser parser) throws DocumentException {
    return mStore.create("testCollection", parser);
  }

  @Override
  public void setUp() throws Exception, FileNotFoundException {
    super.setUp();
    // mTransaction = ((DBCollection) coll).beginTransaction();
  }

  @After
  public void commit() throws DocumentException {
    // mTransaction.commit();
    mStore.close();
  }
}
