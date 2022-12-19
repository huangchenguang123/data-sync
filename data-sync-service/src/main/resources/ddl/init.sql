-- auto-generated definition
create table flow
(
    id        bigint auto_increment comment 'id'
        primary key,
    flow_name varchar(48) not null comment 'flow name',
    config    text        not null comment 'config value',
    enable    int         not null comment 'enable',
    create_at bigint      not null comment 'create timestamp',
    update_at bigint      not null comment 'update timestamp'
);

-- auto-generated definition
create table customize_function
(
    id           bigint auto_increment comment 'id'
        primary key,
    function_name varchar(48) not null comment 'function name',
    script       text        not null comment 'script',
    create_at     bigint      not null comment 'create timestamp',
    update_at     bigint      not null comment 'update timestamp',
    constraint function_name
        unique (function_name)
);

