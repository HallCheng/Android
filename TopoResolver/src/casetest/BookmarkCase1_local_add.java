package casetest;
/**
 * ��������
 * 
 * last:
 * cloud:
 * local: BA
 * expect: BA
 * 
 * @author chedifier
 *
 */
public class BookmarkCase1_local_add extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
					.dirty(0)
					.build());
		caseData.add(new TestBookmarkBuilder("B")
				.local_next("A")
				.dirty(0)
				.build());
	}

}
