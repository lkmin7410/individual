package com.individual.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.individual.connection.ConnectionProvider;
import com.individual.connection.jdbcUtil;
import com.individual.entity.BoardDTO;
import com.individual.entity.ContentDTO;
import com.individual.entity.Forum_commentDTO;
import com.individual.entity.UserBeans;
import com.individual.entity.imgDTO;

public class Service {
	private String url = "jdbc:mysql://3.34.54.186:3306/project";
	private String root = "lkmin7410";
	private String pw = "!qlalf45306380";
	private String driver = "com.mysql.jdbc.Driver";

//	Connection con = DriverManager.getConnection(url, root, pw);

	private static Service instance = new Service();

	public static Service getInstance() {
		return instance;
	}

	public int idCheck(String userID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int value = 0;

		try {
			String sql = "select id from user where id = ?";

			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next())
				value = 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return value;
	}

	public void signup(UserBeans ub) {
		String sql = "insert into user(id,pw,name,email,flag) values(?,?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, ub.getId());
			psmt.setString(2, ub.getPw());
			psmt.setString(3, ub.getName());
			psmt.setString(4, ub.getEmail());
			psmt.setString(5, "Y");

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//????????? ?????????
	public void naver_login(UserBeans ub) {
		String sql = "insert into user(id,naver_name,naver_email,naver_nickname) values(?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, ub.getId());
			psmt.setString(2, ub.getNaver_name());
			psmt.setString(3, ub.getNaver_email());
			psmt.setString(4, ub.getNaver_nickname());

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(UserBeans ub) {
		String sql = "select pw from user where id=?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, ub.getId());

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(ub.getPw())) {
					return 1;
				} else {
					return 0; // ????????????
				}
			}
			return -1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // db??????
	}

	// ?????? ????????? ???????????? db??? ??????
	public void insertSmallimg(List<imgDTO> insert_list) {
		String sql = "insert into small_img(img_name,img_path) values(?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			for (int i = 0; i < insert_list.size(); i++) {
				psmt.setString(1, insert_list.get(i).getImg_name());
				psmt.setString(2, insert_list.get(i).getImg_path());
				psmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ??? ????????? ???????????? db??? ??????
	public void insertFullimg(List<imgDTO> insert_list) {
		String sql = "insert into full_img(img_name,img_path) values(?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			for (int i = 0; i < insert_list.size(); i++) {
				psmt.setString(1, insert_list.get(i).getImg_name());
				psmt.setString(2, insert_list.get(i).getImg_path());
				psmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ?????? ???????????? ????????? ?????????
	public List<imgDTO> getImgPath() {
		String sql = "select *  from  small_img order by seq desc";

		List<imgDTO> list = new ArrayList<imgDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				imgDTO io = new imgDTO();
				io.setImg_id(rs.getString("img_id"));
				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setRegdate(rs.getDate("regdate"));
				list.add(io);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// ??? ???????????? ????????? ?????????
	public imgDTO getImgPath_full(String img_name) {
		String sql = "select *  from full_img where img_name=?";

		imgDTO io = new imgDTO();

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, img_name);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				io.setImg_id(rs.getString("img_id"));
				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setRegdate(rs.getDate("regdate"));
				System.out.println(io.getImg_name());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return io;
	}

	
	// top ????????? ??? ????????????
	// ????????? ????????? 
	public List<imgDTO> getImgPath(int page, int result) {

		int startNum = page + (page - 1) * 19;
		int lastNum = startNum + 18;

		String sql = "select * from (select @rownum:=@rownum+1 as rownum, n.* from"
				+ " (select d.hit,s.* from detail d, small_img s where d.img_name = s.img_name order by hit desc)n ,"
				+ " (select @rownum:=0)as r) s" + " where rownum between ? and ?";
		
		//having 
		
		List<imgDTO> list = new ArrayList<imgDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setInt(1, startNum);
			psmt.setInt(2, lastNum);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				imgDTO io = new imgDTO();
				io.setImg_id(rs.getString("img_id"));
				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setRegdate(rs.getDate("regdate"));

				list.add(io);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//new list ????????? ????????????
	public List<imgDTO> New_getImgPath(int page, int result) {

		int startNum = result - (page - 1) * 19;
		int lastNum = (startNum - 18);

		String sql = "select * from small_img where seq between ? and ? order by seq desc";

		List<imgDTO> list = new ArrayList<imgDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setInt(2, startNum);
			psmt.setInt(1, lastNum);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				imgDTO io = new imgDTO();
				io.setImg_id(rs.getString("img_id"));
				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setRegdate(rs.getDate("regdate"));

				list.add(io);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// ???????????? ????????? ?????? ????????????
	public List<imgDTO> getImgPath_Random(int page, int result) {

		int startNum = result - (page - 1) * 19;
		int lastNum = (startNum - 18);

		String sql = "select * from small_img order by rand() limit ?";

		List<imgDTO> list = new ArrayList<imgDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setInt(1, result);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				imgDTO io = new imgDTO();
				io.setImg_id(rs.getString("img_id"));
				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setRegdate(rs.getDate("regdate"));
				list.add(io);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// ?????? ???????????? ??????(?????????)??? ????????????, ????????? ????????? ?????????
	public int contentCount() {
		int result = 0;
		String sql = "select count(seq) as count from small_img";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ???????????? ??? ????????????
	public void insertDetail(List<imgDTO> f_insert_list) {
		String sql = "insert into detail(img_name,width,height,tags,uploader) values(?,?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			for (int i = 0; i < f_insert_list.size(); i++) {
				psmt.setString(1, f_insert_list.get(i).getImg_name());
				psmt.setString(2, f_insert_list.get(i).getWidth());
				psmt.setString(3, f_insert_list.get(i).getHeight());
				psmt.setString(4, f_insert_list.get(i).getTags());
				psmt.setString(5, f_insert_list.get(i).getUploader());

				psmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ????????? ???????????? ???????????? ?????????
	public imgDTO detail_info(String img_name) {
		String sql = "select * from detail where img_name=?";

		imgDTO io = new imgDTO();

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, img_name);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				io.setImg_name(rs.getString("img_name"));
				io.setWidth(rs.getString("width"));
				io.setHeight(rs.getString("height"));
				io.setUploader(rs.getString("uploader"));
				io.setHit(rs.getInt("hit"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return io;
	}

	// ?????????
	public void click_count(String img_name) {
		String sql = "update detail set hit=(hit+1) where img_name=?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, img_name);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ??????
	public List<imgDTO> detail_info_tag(String img_name) {
		String sql = "select *  from  detail where img_name=?";
		List<imgDTO> list = new ArrayList<imgDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setString(1, img_name);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {

				int a = 0;
				String tag_s[] = rs.getString("tags").split(",");
				for (int i = 0; i < tag_s.length; i++) {
					imgDTO io = new imgDTO();
//						System.out.println("ForEach - tag"+ (i) + "?????? :: "+ tag_s[i]);
					io.setTag(tag_s[i]);
					list.add(io);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// ??????
	public List<imgDTO> search(String sc, int page) {
		List<imgDTO> list_sc = new ArrayList<imgDTO>();
		int startNum = page + (page - 1) * 19;
		int lastNum = startNum + 18;

		String sql = "select *" + " from (select @rownum:=@rownum+1 as rownum, n.* from"
				+ " (select d.tags,d.img_name, s.img_path from detail d, small_img s where d.img_name = s.img_name and d.tags like ?)n ,"
				+ " (select @rownum:=0)as r) s" 
				+ " where rownum between ? and ?";

//		String sql ="select d.tags,d.img_name, s.img_path from detail d, small_img s where d.img_name = s.img_name"
//				  + " and d.tags like ?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setString(1, "%" + sc + "%");
			psmt.setInt(2, startNum);
			psmt.setInt(3, lastNum);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				imgDTO io = new imgDTO();

				io.setImg_name(rs.getString("img_name"));
				io.setImg_path(rs.getString("img_path"));
				io.setTags(rs.getString("tags"));
				io.setSearch(sc);
				list_sc.add(io);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list_sc;
	}
	
	public String Search_translate(String sc) {
		
		String sql = "select * from Search_translate where kor = ?";
		String str = "";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, sc);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				str = rs.getString("eng");
				
			}
			if(str == null || str.equals(null)||str == "" || str.equals("")) {
				str = sc;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// ?????? ??? ???????????? ?????????
	public int contentCount_search(String sc) {
		int result = 0;
		String sql = "select count(c.img_name)" + " from (select distinct d.tags,d.img_name, s.img_path"
				+ " from detail d, small_img s where d.img_name = s.img_name and d.tags like ?) as c";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + sc + "%");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ?????? ????????????
	public List<BoardDTO> comment_list(String img_name) {

		String sql = "select * from board where img_name = ?";

		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, img_name);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				BoardDTO bd = new BoardDTO();
				bd.setWid(rs.getInt(1));
				bd.setImg_name(rs.getString(2));
				bd.setComment(rs.getString(3));
				bd.setScore(rs.getInt(4));
				bd.setRegdate(rs.getDate(5));
				bd.setUserid(rs.getString(6));
				list.add(bd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// ?????? ??????
	public void comment_write(BoardDTO bd) {

		String sql = "insert into board(img_name,comment,score,userid) values(?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, bd.getImg_name());
			psmt.setString(2, bd.getComment());
			psmt.setInt(3, bd.getScore());
			psmt.setString(4, bd.getUserid());
			
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//??? ??????
	public void content_write(ContentDTO cd) {

		String sql = "insert into forum(title,content,writer,img) values(?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, cd.getTitle());
			psmt.setString(2, cd.getContent());
			psmt.setString(3, cd.getWriter());
			psmt.setString(4, cd.getImg());
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//??? ????????????
	public void edit_content_write(ContentDTO cd) {

		String sql = "UPDATE forum SET title=?, content=?, img=?, WHERE seq=? LIMIT 1;";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, cd.getTitle());
			psmt.setString(2, cd.getContent());
			psmt.setString(3, cd.getImg());
			psmt.setInt(4, cd.getSeq());
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//??? ????????????
		public void remove_content_write(int seq) {

			String sql = "UPDATE forum SET delflag=? WHERE seq=? LIMIT 1;";

			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, root, pw);
				PreparedStatement psmt = con.prepareStatement(sql);
				
				psmt.setString(1, "Y");
				psmt.setInt(2, seq);
			
				psmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//??? ?????? ?????????
	public int forumCount(String sc) {
		int result = 0;
		String sql = "select count(c.title) from (select distinct *"
				+ "	from forum where title like ? and delflag='N') as c";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + sc + "%");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	
	//??? ?????? ????????????
	public List<ContentDTO> getForum_list(int page, int result,String sc) {

		int startNum = result - (page - 1) * 10;
		int lastNum = (startNum - 9);

		String sql = "select * from (select @rownum:=@rownum+1 as rownum, n.* from"
				+ " (select * from forum where title like ? and delflag = 'N')n ,"
				+ "	(select @rownum:=0)as r) s"
				+ "	where rownum between ? and ? order by rownum desc";
		
//		String sql = "select * from forum where seq between ? and ? order by seq desc";

		List<ContentDTO> list = new ArrayList<ContentDTO>();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, "%"+sc+"%");
			psmt.setInt(3, startNum);
			psmt.setInt(2, lastNum);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				ContentDTO cd = new ContentDTO();
				cd.setSeq(rs.getInt("seq"));
				cd.setTitle(rs.getString("title"));
				cd.setWriter(rs.getString("writer"));
				cd.setRegdate(rs.getDate("regdate"));
				cd.setHit(rs.getInt("hit"));
				list.add(cd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//??? ?????? ????????????
	public ContentDTO Forum_detail_info(String seq) {
		String sql = "select * from forum where seq=?";

		ContentDTO cd = new ContentDTO();

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				cd.setSeq(rs.getInt("seq"));
				cd.setTitle(rs.getString("title"));
				cd.setContent(rs.getString("content"));
				cd.setRegdate(rs.getDate("regdate"));
				cd.setWriter(rs.getString("writer"));
				cd.setHit(rs.getInt("hit"));
				cd.setImg(rs.getString("img"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cd;
	}
	
	
	//???????????? ????????????
	public void forum_comment_write(Forum_commentDTO fd) {

		String sql = "insert into forum_comment(seq,img_name,comment,userid) values(?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, fd.getSeq());
			psmt.setString(2, fd.getImg_name());
			psmt.setString(3, fd.getComment());
			psmt.setString(4, fd.getUserid());
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//???????????? ?????? ????????? ????????????
	public List<Forum_commentDTO> forum_comment_list(String seq) {

		String sql = "select * from forum_comment where seq = ? order by regdate desc";

		List<Forum_commentDTO> list = new ArrayList<Forum_commentDTO>();

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				Forum_commentDTO fd = new Forum_commentDTO();
				fd.setSeq(rs.getString(1));
				fd.setImg_name(rs.getString(2));
				fd.setComment(rs.getString(3));
				fd.setRegdate(rs.getDate(4));
				fd.setUserid(rs.getString(5));
				list.add(fd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	//????????? ?????????
	public void forum_click_count(String seq) {
		String sql = "update forum set hit=(hit+1) where seq=?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, root, pw);

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, seq);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
