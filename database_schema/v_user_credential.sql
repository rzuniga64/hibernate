CREATE
	ALGORITHM = UNDEFINED
    DEFINER = 'ifinances'@'%'
    SQL SECURITY DEFINER
VIEW v_user_credential AS
	SELECT
		finances_user.USER_ID AS 'user_id',
        finances_user.FIRST_NAME AS 'first_name',
        finances_user.LAST_NAME AS 'last_name',
        credential.USERNAME AS 'USERNAME',
        credential.PASSWORD AS 'PASSWORD'
	FROM
    (finances_user JOIN credential ON ((finances_user.USER_ID = credential.USER_ID)))