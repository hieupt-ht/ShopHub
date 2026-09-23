ALTER TABLE refresh_tokens
DROP CONSTRAINT DF__refresh_t__creat__5535A963;

ALTER TABLE refresh_tokens
ALTER COLUMN created_at DATETIMEOFFSET(7) NOT NULL;

ALTER TABLE refresh_tokens
ALTER COLUMN expires_at DATETIMEOFFSET(7) NOT NULL;

ALTER TABLE refresh_tokens
ADD CONSTRAINT DF_refresh_tokens_created_at
    DEFAULT SYSDATETIMEOFFSET() FOR created_at;