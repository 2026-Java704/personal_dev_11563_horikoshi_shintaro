-- カテゴリーテーブルデータ
INSERT INTO genres(genre_name, is_income) VALUES('給与', TRUE);
INSERT INTO genres(genre_name, is_income) VALUES('固定費', FALSE);
INSERT INTO genres(genre_name, is_income) VALUES('変動費', FALSE);
INSERT INTO genres(genre_name, is_income) VALUES('その他の収入', TRUE);
INSERT INTO genres(genre_name, is_income) VALUES('その他の支出', FALSE);
INSERT INTO genres(genre_name, is_income) VALUES('臨時収入', TRUE);

-- ユーザテーブルデータ
INSERT INTO users(user_name,email,password) VALUES('田中太郎', 'tanaka@aaa.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('鈴木一郎', 'suzuki@aaa.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('test', 'test','test');
INSERT INTO users(user_name,email,password) VALUES('test2', 'test2','test2');

-- 項目テーブルデータ
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('食事代', 1, 3, 1200, '2026/05/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('給料', 2, 1, 200000, '2026/05/15');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('食事代', 3, 3, 1200, '2026/05/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('給料', 3, 1, 200000, '2026/05/15');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('プレゼント代', 3, 3, 3000, '2026/4/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('アプリ収入', 3, 6, 2300, '2026/4/20');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('映画代', 4, 3, 1600, '2026/05/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('お小遣い', 4, 4, 2000, '2026/05/15');
