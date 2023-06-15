--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Ubuntu 15.3-1.pgdg20.04+1)
-- Dumped by pg_dump version 15.3 (Ubuntu 15.3-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: joko_security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA joko_security;


ALTER SCHEMA joko_security OWNER TO postgres;

--
-- Name: profile; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA profile;


ALTER SCHEMA profile OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: audit_session; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.audit_session (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    remote_ip character varying(255),
    user_agent character varying(255),
    user_date timestamp without time zone,
    id_principal bigint
);


ALTER TABLE joko_security.audit_session OWNER TO postgres;

--
-- Name: TABLE audit_session; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.audit_session IS 'Stores the last login of a given user';


--
-- Name: audit_session_id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.audit_session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.audit_session_id_seq OWNER TO postgres;

--
-- Name: audit_session_id_seq; Type: SEQUENCE OWNED BY; Schema: joko_security; Owner: postgres
--

ALTER SEQUENCE joko_security.audit_session_id_seq OWNED BY joko_security.audit_session.id;


--
-- Name: consumer_api; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.consumer_api (
    id bigint NOT NULL,
    access_level character varying(255),
    consumer_id character varying(255),
    contact_name character varying(255),
    document_number character varying(255),
    name character varying(255),
    secret character varying(255)
);


ALTER TABLE joko_security.consumer_api OWNER TO postgres;

--
-- Name: TABLE consumer_api; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.consumer_api IS 'guarda los consumer para integracion con terceros a nivel de API';


--
-- Name: consumer_api_id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.consumer_api_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.consumer_api_id_seq OWNER TO postgres;

--
-- Name: consumer_api_id_seq; Type: SEQUENCE OWNED BY; Schema: joko_security; Owner: postgres
--

ALTER SEQUENCE joko_security.consumer_api_id_seq OWNED BY joko_security.consumer_api.id;


--
-- Name: id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.id_seq OWNER TO postgres;

--
-- Name: keychain; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.keychain (
    id integer NOT NULL,
    value character varying(500)
);


ALTER TABLE joko_security.keychain OWNER TO postgres;

--
-- Name: TABLE keychain; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.keychain IS 'Guarda la clave para firmar los tokens en caso sea modo BD';


--
-- Name: principal_session; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.principal_session (
    id bigint NOT NULL,
    app_description character varying(255),
    app_id character varying(255),
    user_description character varying(255),
    user_id character varying(255)
);


ALTER TABLE joko_security.principal_session OWNER TO postgres;

--
-- Name: principal_session_id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.principal_session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.principal_session_id_seq OWNER TO postgres;

--
-- Name: principal_session_id_seq; Type: SEQUENCE OWNED BY; Schema: joko_security; Owner: postgres
--

ALTER SEQUENCE joko_security.principal_session_id_seq OWNED BY joko_security.principal_session.id;


--
-- Name: security_profile; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.security_profile (
    id bigint NOT NULL,
    access_token_timeout_seconds integer,
    key character varying(255),
    max_access_token_requests integer,
    max_number_of_connections integer,
    max_number_devices_user integer,
    name character varying(255),
    refresh_token_timeout_seconds integer,
    revocable boolean
);


ALTER TABLE joko_security.security_profile OWNER TO postgres;

--
-- Name: TABLE security_profile; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.security_profile IS 'Establece la configuracion de emision de tokens para los distintos ambientes';


--
-- Name: security_profile_id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.security_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.security_profile_id_seq OWNER TO postgres;

--
-- Name: security_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: joko_security; Owner: postgres
--

ALTER SEQUENCE joko_security.security_profile_id_seq OWNED BY joko_security.security_profile.id;


--
-- Name: seed; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.seed (
    id bigint NOT NULL,
    user_id character varying(255),
    seed_secret character varying(255)
);


ALTER TABLE joko_security.seed OWNER TO postgres;

--
-- Name: TABLE seed; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.seed IS 'Guarda las semillas OTP';


--
-- Name: seed_id_seq; Type: SEQUENCE; Schema: joko_security; Owner: postgres
--

CREATE SEQUENCE joko_security.seed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE joko_security.seed_id_seq OWNER TO postgres;

--
-- Name: seed_id_seq; Type: SEQUENCE OWNED BY; Schema: joko_security; Owner: postgres
--

ALTER SEQUENCE joko_security.seed_id_seq OWNED BY joko_security.seed.id;


--
-- Name: tokens; Type: TABLE; Schema: joko_security; Owner: postgres
--

CREATE TABLE joko_security.tokens (
    id character varying(255) NOT NULL,
    expiration timestamp without time zone,
    issued_at timestamp without time zone,
    remote_ip character varying(255),
    token_type character varying(255),
    user_agent character varying(255),
    user_id character varying(255),
    security_profile_id bigint
);


ALTER TABLE joko_security.tokens OWNER TO postgres;

--
-- Name: TABLE tokens; Type: COMMENT; Schema: joko_security; Owner: postgres
--

COMMENT ON TABLE joko_security.tokens IS 'La lista de tokens de refresh que estan activos';


--
-- Name: rol; Type: TABLE; Schema: profile; Owner: postgres
--

CREATE TABLE profile.rol (
    rol_id integer NOT NULL,
    description character varying(40)
);


ALTER TABLE profile.rol OWNER TO postgres;

--
-- Name: rol_rol_id_seq; Type: SEQUENCE; Schema: profile; Owner: postgres
--

CREATE SEQUENCE profile.rol_rol_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile.rol_rol_id_seq OWNER TO postgres;

--
-- Name: rol_rol_id_seq; Type: SEQUENCE OWNED BY; Schema: profile; Owner: postgres
--

ALTER SEQUENCE profile.rol_rol_id_seq OWNED BY profile.rol.rol_id;


--
-- Name: user; Type: TABLE; Schema: profile; Owner: postgres
--

CREATE TABLE profile."user" (
    user_id integer NOT NULL,
    username character varying(255),
    email character varying(255) NOT NULL,
    password character varying(500) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_access timestamp without time zone,
    status character varying(255) NOT NULL
);


ALTER TABLE profile."user" OWNER TO postgres;

--
-- Name: user_rol; Type: TABLE; Schema: profile; Owner: postgres
--

CREATE TABLE profile.user_rol (
    user_id integer NOT NULL,
    rol_id integer NOT NULL
);


ALTER TABLE profile.user_rol OWNER TO postgres;

--
-- Name: user_userid_seq; Type: SEQUENCE; Schema: profile; Owner: postgres
--

CREATE SEQUENCE profile.user_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile.user_userid_seq OWNER TO postgres;

--
-- Name: user_userid_seq; Type: SEQUENCE OWNED BY; Schema: profile; Owner: postgres
--

ALTER SEQUENCE profile.user_userid_seq OWNED BY profile."user".user_id;


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: audit_session id; Type: DEFAULT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.audit_session ALTER COLUMN id SET DEFAULT nextval('joko_security.audit_session_id_seq'::regclass);


--
-- Name: consumer_api id; Type: DEFAULT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.consumer_api ALTER COLUMN id SET DEFAULT nextval('joko_security.consumer_api_id_seq'::regclass);


--
-- Name: principal_session id; Type: DEFAULT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.principal_session ALTER COLUMN id SET DEFAULT nextval('joko_security.principal_session_id_seq'::regclass);


--
-- Name: security_profile id; Type: DEFAULT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.security_profile ALTER COLUMN id SET DEFAULT nextval('joko_security.security_profile_id_seq'::regclass);


--
-- Name: seed id; Type: DEFAULT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.seed ALTER COLUMN id SET DEFAULT nextval('joko_security.seed_id_seq'::regclass);


--
-- Name: rol rol_id; Type: DEFAULT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.rol ALTER COLUMN rol_id SET DEFAULT nextval('profile.rol_rol_id_seq'::regclass);


--
-- Name: user user_id; Type: DEFAULT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile."user" ALTER COLUMN user_id SET DEFAULT nextval('profile.user_userid_seq'::regclass);


--
-- Data for Name: audit_session; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.audit_session (id, creation_date, remote_ip, user_agent, user_date, id_principal) FROM stdin;
13	2023-06-13 18:17:22.896	\N	\N	2023-06-13 18:17:22.915	3
14	2023-06-13 18:19:38.16	\N	\N	2023-06-13 18:19:38.191	3
15	2023-06-13 18:39:14.265	\N	\N	2023-06-13 18:39:14.295	4
16	2023-06-13 18:44:56.774	\N	\N	2023-06-13 18:44:56.801	4
17	2023-06-13 18:47:44.229	\N	\N	2023-06-13 18:47:44.248	4
18	2023-06-13 18:55:59.434	\N	\N	2023-06-13 18:55:59.453	4
19	2023-06-13 18:57:13.203	\N	\N	2023-06-13 18:57:13.224	3
20	2023-06-13 18:59:18.801	\N	\N	2023-06-13 18:59:18.838	3
21	2023-06-13 19:06:02.553	\N	\N	2023-06-13 19:06:02.578	3
22	2023-06-13 19:11:40.657	\N	\N	2023-06-13 19:11:40.679	3
23	2023-06-13 19:13:21.218	\N	\N	2023-06-13 19:13:21.231	4
24	2023-06-13 22:00:04.349	\N	\N	2023-06-13 22:00:04.353	4
25	2023-06-13 23:43:19.575	\N	\N	2023-06-13 23:43:19.583	4
26	2023-06-13 23:45:13.812	\N	\N	2023-06-13 23:45:13.822	4
27	2023-06-13 23:52:08.629	\N	\N	2023-06-13 23:52:08.636	4
28	2023-06-14 00:05:36.136	\N	\N	2023-06-14 00:05:36.149	4
29	2023-06-14 00:06:44.527	\N	\N	2023-06-14 00:06:44.538	4
30	2023-06-14 00:37:38.6	\N	\N	2023-06-14 00:37:38.614	4
31	2023-06-14 00:39:28.574	\N	\N	2023-06-14 00:39:28.585	3
32	2023-06-14 00:56:10.938	\N	\N	2023-06-14 00:56:10.94	4
33	2023-06-14 01:46:24.812	\N	\N	2023-06-14 01:46:24.818	4
34	2023-06-14 01:49:25.805	\N	\N	2023-06-14 01:49:25.816	4
35	2023-06-14 02:01:15.341	\N	\N	2023-06-14 02:01:15.348	3
36	2023-06-14 02:02:53.195	\N	\N	2023-06-14 02:02:53.196	4
37	2023-06-14 02:18:16.698	\N	\N	2023-06-14 02:18:16.705	3
38	2023-06-14 02:22:27.661	\N	\N	2023-06-14 02:22:27.671	3
\.


--
-- Data for Name: consumer_api; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.consumer_api (id, access_level, consumer_id, contact_name, document_number, name, secret) FROM stdin;
\.


--
-- Data for Name: keychain; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.keychain (id, value) FROM stdin;
\.


--
-- Data for Name: principal_session; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.principal_session (id, app_description, app_id, user_description, user_id) FROM stdin;
3	joko_starter_backend	1	user_id	9
4	joko_starter_backend	1	user_id	10
\.


--
-- Data for Name: security_profile; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.security_profile (id, access_token_timeout_seconds, key, max_access_token_requests, max_number_of_connections, max_number_devices_user, name, refresh_token_timeout_seconds, revocable) FROM stdin;
1	14440	DEFAULT	2	\N	1	Default configuration(testing purposes)	300	f
\.


--
-- Data for Name: seed; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.seed (id, user_id, seed_secret) FROM stdin;
\.


--
-- Data for Name: tokens; Type: TABLE DATA; Schema: joko_security; Owner: postgres
--

COPY joko_security.tokens (id, expiration, issued_at, remote_ip, token_type, user_agent, user_id, security_profile_id) FROM stdin;
\.


--
-- Data for Name: rol; Type: TABLE DATA; Schema: profile; Owner: postgres
--

COPY profile.rol (rol_id, description) FROM stdin;
1	ADMIN
2	NORMAL
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: profile; Owner: postgres
--

COPY profile."user" (user_id, username, email, password, creation_date, last_access, status) FROM stdin;
10	dev	dev@dev.com	$2a$06$8JEVv/6t/C/7YzoistARweaRfgoDgn1caIezbW0/xV/fdprZfkzqa	2023-06-13 18:23:39.206554	2023-06-14 02:02:53	ACTIVO
9	admin	admin@admin.com	$2a$06$8JEVv/6t/C/7YzoistARweaRfgoDgn1caIezbW0/xV/fdprZfkzqa	2023-06-13 00:13:17.103734	2023-06-14 02:22:27	ACTIVO
\.


--
-- Data for Name: user_rol; Type: TABLE DATA; Schema: profile; Owner: postgres
--

COPY profile.user_rol (user_id, rol_id) FROM stdin;
9	1
10	2
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
0	afeltes	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.033212	1	EXECUTED	7:56a3f48c3adcaf36d1a6717cfad18e6c	sql		\N	3.2.0
1518643732286-100	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.057056	2	EXECUTED	7:ecfd10da8ee81fd751d571d05d0e0175	createSequence		\N	3.2.0
1518643732286-6	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.084514	3	EXECUTED	7:01fb1819b29f549cccc1b3386a82bf13	createTable		\N	3.2.0
1518643732286-12	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.115976	4	EXECUTED	7:2584d2791355bd635a434945cd111d9c	addPrimaryKey		\N	3.2.0
1518643732286-7	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.130552	5	EXECUTED	7:64d090dabdf71153b6cb5eb62641ba49	createTable		\N	3.2.0
1518643732286-13	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.158083	6	EXECUTED	7:05250aa9bd6d4bcfb94a3ee15e764fd5	addPrimaryKey		\N	3.2.0
1518643732286-8	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.190077	7	EXECUTED	7:efb693dd2f5a14672732d1425602e558	createTable		\N	3.2.0
1518643732286-14	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.217693	8	EXECUTED	7:7d89b79b491993f83bd81e0bcc3a9b90	addPrimaryKey		\N	3.2.0
1518643732286-17	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.249433	9	EXECUTED	7:429f197eba425657944982850bdd1ead	addUniqueConstraint		\N	3.2.0
1518643732286-9	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.28418	10	EXECUTED	7:e1edc19afc26958d355d514f982c1400	createTable		\N	3.2.0
1518643732286-15	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.312926	11	EXECUTED	7:9b95578067134795737bb7323d89aead	addPrimaryKey		\N	3.2.0
1518643732286-10	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.34859	12	EXECUTED	7:281887838ac3472999fcc06c3019c173	createTable		\N	3.2.0
1518643732286-16	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.373487	13	EXECUTED	7:a1a4f56faf541676fb502991c3136640	addPrimaryKey		\N	3.2.0
1518643732286-19	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.390314	14	EXECUTED	7:f9d97cebb53dcad4f386630143a8b425	addForeignKeyConstraint		\N	3.2.0
1518643732286-101	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.421341	15	EXECUTED	7:cb2ac869ad774bb4964e27bf576dbf86	createTable		\N	3.2.0
1518643732286-102	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.446174	16	EXECUTED	7:b7ef8c2e434f879c06ab5ed01933b060	addPrimaryKey		\N	3.2.0
1518643732286-5	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.47952	17	EXECUTED	7:a06305fdd842ef91f73f7b7a04876fd9	createTable		\N	3.2.0
1518643732286-11	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.511534	18	EXECUTED	7:4f05c5cada66b285c4bcca54ebdce089	addPrimaryKey		\N	3.2.0
1518643732286-18	danicricco	src/main/resources/db/liquibase/db-changelog-inicial.xml	2023-04-03 10:27:13.530353	19	EXECUTED	7:7dd7c321b38caadeb4f54b294391b2b1	addForeignKeyConstraint		\N	3.2.0
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Name: audit_session_id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.audit_session_id_seq', 38, true);


--
-- Name: consumer_api_id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.consumer_api_id_seq', 1, false);


--
-- Name: id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.id_seq', 1, false);


--
-- Name: principal_session_id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.principal_session_id_seq', 4, true);


--
-- Name: security_profile_id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.security_profile_id_seq', 1, false);


--
-- Name: seed_id_seq; Type: SEQUENCE SET; Schema: joko_security; Owner: postgres
--

SELECT pg_catalog.setval('joko_security.seed_id_seq', 1, false);


--
-- Name: rol_rol_id_seq; Type: SEQUENCE SET; Schema: profile; Owner: postgres
--

SELECT pg_catalog.setval('profile.rol_rol_id_seq', 2, true);


--
-- Name: user_userid_seq; Type: SEQUENCE SET; Schema: profile; Owner: postgres
--

SELECT pg_catalog.setval('profile.user_userid_seq', 11, true);


--
-- Name: audit_session audit_session_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.audit_session
    ADD CONSTRAINT audit_session_pkey PRIMARY KEY (id);


--
-- Name: consumer_api consumer_api_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.consumer_api
    ADD CONSTRAINT consumer_api_pkey PRIMARY KEY (id);


--
-- Name: keychain keychain_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.keychain
    ADD CONSTRAINT keychain_pkey PRIMARY KEY (id);


--
-- Name: principal_session principal_session_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.principal_session
    ADD CONSTRAINT principal_session_pkey PRIMARY KEY (id);


--
-- Name: security_profile security_profile_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.security_profile
    ADD CONSTRAINT security_profile_pkey PRIMARY KEY (id);


--
-- Name: seed seed_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.seed
    ADD CONSTRAINT seed_pkey PRIMARY KEY (id);


--
-- Name: tokens tokens_pkey; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (id);


--
-- Name: principal_session uk_muajvqvs1jntexdohty6hexrv; Type: CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.principal_session
    ADD CONSTRAINT uk_muajvqvs1jntexdohty6hexrv UNIQUE (app_id, user_id);


--
-- Name: rol rol_description_key; Type: CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.rol
    ADD CONSTRAINT rol_description_key UNIQUE (description);


--
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (rol_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: user_rol user_rol_pkey; Type: CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.user_rol
    ADD CONSTRAINT user_rol_pkey PRIMARY KEY (user_id, rol_id);


--
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: audit_session fk_audit_principalsession; Type: FK CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.audit_session
    ADD CONSTRAINT fk_audit_principalsession FOREIGN KEY (id_principal) REFERENCES joko_security.principal_session(id);


--
-- Name: tokens fk_tokens_securityprofile; Type: FK CONSTRAINT; Schema: joko_security; Owner: postgres
--

ALTER TABLE ONLY joko_security.tokens
    ADD CONSTRAINT fk_tokens_securityprofile FOREIGN KEY (security_profile_id) REFERENCES joko_security.security_profile(id);


--
-- Name: user_rol user_rol_rol_id_fkey; Type: FK CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.user_rol
    ADD CONSTRAINT user_rol_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES profile.rol(rol_id);


--
-- Name: user_rol user_rol_user_id_fkey; Type: FK CONSTRAINT; Schema: profile; Owner: postgres
--

ALTER TABLE ONLY profile.user_rol
    ADD CONSTRAINT user_rol_user_id_fkey FOREIGN KEY (user_id) REFERENCES profile."user"(user_id);


--
-- PostgreSQL database dump complete
--

