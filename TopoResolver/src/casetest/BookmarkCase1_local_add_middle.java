package casetest;

/**
 * ��������
 * 
 * last:AB			1
 * cloud:���޸ģ�AB	1
 * local: ����C: ACB	2
 * expect: ACB
 * 
 * @author chedifier
 *
 */
public class BookmarkCase1_local_add_middle extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
					.local_next("C")
					.order_time(2L)
					.cloud_next(null)
					.cloud_order_time(1L)
					.dirty(0)
					.build());
		
		caseData.add(new TestBookmarkBuilder("B")
				.local_next(null)
				.order_time(1L)
				.cloud_next(null)
				.cloud_order_time(1L)
				.dirty(0)
				.build());
		
		caseData.add(new TestBookmarkBuilder("C")
				.local_next("B")
				.order_time(2L)
				.cloud_next(null)
				.cloud_order_time(0L)
				.dirty(0)
				.build());
	}

}