package casetest;

/**
 * ����ɾ��
 * last: ABC				1
 * local: ɾ��B��Ϊ:  	AC		2
 * cloud: ���޸�: 		ABC		1
 * expect: AC
 * 
 * 
 * @author chedifier
 *
 */
public class BookmarkCase1_local_del extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
				.dirty(0)
				.local_next("C")
				.order_time(2L)
				.cloud_next("B")
				.cloud_order_time(1L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("C")
				.dirty(0)
				.local_next(null)				
				.order_time(1L)
				.cloud_next(null)
				.cloud_order_time(1L)
				.build());
	}

}

