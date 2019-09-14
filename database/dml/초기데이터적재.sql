/* 사용자 */
INSERT INTO com.comuser(user_id, pwd, user_name, non_expired_yn, non_locked_yn, enabled_yn, pass_non_expired_yn, sys_dt, sys_user, upd_dt, upd_user)
VALUES('1', '1234', 'test', 1, 1, 1, 1, NULL, NULL, '2018-10-26 12:48:34.000', '1');

/* 프로그램 */
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM001', '2018-04-10 23:14:32.000', '1', '2018-10-26 07:35:49.000', '1', '사용자등록', 'userForm', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM002', '2018-04-10 23:15:03.000', '1', '2018-04-10 23:15:03.000', '1', '사용자그리드', 'userList', NULL,'PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM003', '2018-04-10 23:15:28.000', '1', '2018-04-10 23:15:28.000', '1', '권한등록', 'authForm', '권한등록','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM004', '2018-04-10 23:15:40.000', '1', '2018-04-10 23:15:40.000', '1', '권한그리드', 'authList', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM005', '2018-04-10 23:16:00.000', '1', '2018-04-10 23:16:00.000', '1', '메뉴그룹등록', 'menuGroupForm', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM006', '2018-04-10 23:16:10.000', '1', '2018-04-10 23:16:10.000', '1', '메뉴그룹 그리드', 'menuGroupList', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM007', '2018-04-10 23:16:26.000', '1', '2018-04-10 23:16:26.000', '1', '메뉴등록', 'menuForm', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM008', '2018-04-10 23:16:54.000', '1', '2018-04-10 23:16:54.000', '1', '메뉴그리드', 'menuList', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM009', '2018-04-10 23:17:04.000', '1', '2018-04-10 23:17:04.000', '1', '메뉴트리', 'menuTree', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM010', '2018-04-10 23:17:23.000', '1', '2018-04-10 23:17:23.000', '1', '프로그램등록', 'programForm', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('COM011', '2018-04-10 23:17:35.000', '1', '2018-04-10 23:17:35.000', '1', '프로그램그리드', 'programList', '','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('GRP0001', '2018-10-26 12:47:44.000', '1', '2018-10-26 12:47:44.000', '1', '게시판등록', 'boardForm', NULL,'PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('PROGRAM1', '2018-03-01 08:33:43.000', '1', '2018-03-16 23:47:42.000', '1', '프로그램1', 'home', NULL,'PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('PROGRAM2', '2018-03-03 23:06:48.000', '1', '2018-03-16 23:47:49.000', '1', '프로그램2', 'menuForm', '메뉴 트리','PROGRAM');
INSERT INTO com.comresource
(resource_code, sys_dt, sys_user, upd_dt, upd_user, resource_name, url, description,resource_type)
VALUES('PROGRAM3', '2018-03-16 23:49:18.000', '1', '2018-03-16 23:49:18.000', '1', '메뉴트리', 'menuTree', NULL,'PROGRAM');


/* 메뉴 그룹 */
INSERT INTO com.commenugroup(sys_dt, sys_user, upd_dt, upd_user, menu_group_code, menu_group_name, description)
VALUES('2018-10-26 07:25:54.602', '1', '2018-10-26 07:25:55.000', '1', 'COM', '공통메뉴그룹', NULL);
INSERT INTO com.commenugroup(sys_dt, sys_user, upd_dt, upd_user, menu_group_code, menu_group_name, description)
VALUES('2018-10-26 12:35:29.771', '1', '2018-10-26 12:35:30.000', '1', 'GRP', '그룹웨어', NULL);

/* 메뉴 */
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0001', 'COM', NULL, NULL, '2018-10-26 07:26:40.000', '1', '2018-10-26 13:51:42.000', '1', '사용자', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0002', 'COM', NULL, NULL, '2018-10-26 07:27:00.000', '1', '2018-10-26 07:27:00.000', '1', '메뉴', 2, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0003', 'COM', NULL, NULL, '2018-10-26 07:27:12.000', '1', '2018-10-26 07:27:12.000', '1', '프로그램', 3, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0004', 'COM', NULL, NULL, '2018-10-26 07:27:24.000', '1', '2018-10-26 07:27:24.000', '1', '권한', 4, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0005', 'COM', 'COM001', 'COM0001', '2018-10-26 07:28:30.000', '1', '2018-10-26 07:30:23.000', '1', '사용자등록', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0006', 'COM', 'COM005', 'COM0002', '2018-10-26 07:30:59.000', '1', '2018-10-26 07:31:00.000', '1', '메뉴그룹등록', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0007', 'COM', 'COM007', 'COM0002', '2018-10-26 07:31:18.000', '1', '2018-10-26 07:31:19.000', '1', '메뉴등록', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0008', 'COM', 'COM010', 'COM0003', '2018-10-26 07:31:37.000', '1', '2018-10-26 07:31:38.000', '1', '프로그램등록', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('COM0009', 'COM', 'COM003', 'COM0004', '2018-10-26 07:32:52.000', '1', '2018-10-26 07:32:54.000', '1', '권한등록', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('GRP0001', 'GRP', NULL, NULL, '2018-10-26 12:46:10.000', '1', '2018-10-26 12:46:10.000', '1', '게시판', 1, 0,'ITEM');
INSERT INTO com.commenu(menu_code, menu_group_code, resource_code, p_menu_code, sys_dt, sys_user, upd_dt, upd_user, menu_name, seq, lvl,menu_type)
VALUES('GRP0002', 'GRP', 'GRP0001', 'GRP0001', '2018-10-26 12:46:45.000', '1', '2018-10-26 12:49:12.000', '1', '게시판등록', 1, 0,'ITEM');

/* 권한 */
INSERT INTO com.comauthority(authority_name, description, sys_dt, sys_user, upd_dt, upd_user)
VALUES('ROLE_TEST', '테스트 유저 권한', NULL, NULL, '2018-01-27 00:45:27.000', '1');
INSERT INTO com.comauthority(authority_name, description, sys_dt, sys_user, upd_dt, upd_user)
VALUES('ROLE_USER', '기본 로그인 권한', '2018-01-27 00:45:10.000', '1', '2018-01-27 00:50:39.000', '1');

/* 사용자_권한 */
INSERT INTO com.comuserauthority(user_id, authority_name)
VALUES('1', 'ROLE_USER');

/* 사용자_메뉴그룹 */
INSERT INTO com.comusermenugroup(user_id, menu_group_code)
VALUES('1', 'COM');
INSERT INTO com.comusermenugroup(user_id, menu_group_code)
VALUES('1', 'GRP');




