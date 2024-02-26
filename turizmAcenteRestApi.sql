PGDMP                      |            turizmAcenteRestApi    16.1    16.1 %               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    26821    turizmAcenteRestApi    DATABASE     �   CREATE DATABASE "turizmAcenteRestApi" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Australia.1252';
 %   DROP DATABASE "turizmAcenteRestApi";
                admin    false            �            1259    26953    hotel2pensions    TABLE     �   CREATE TABLE public.hotel2pensions (
    hotel2pensions_hotel_id bigint NOT NULL,
    hotel2pensions_pension_id bigint NOT NULL
);
 "   DROP TABLE public.hotel2pensions;
       public         heap    postgres    false            �            1259    26933    hotels    TABLE     �  CREATE TABLE public.hotels (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    hotel_address character varying(255) NOT NULL,
    hotel_city character varying(255) NOT NULL,
    hotel_email character varying(255) NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_name character varying(255) NOT NULL,
    hotel_otopark boolean NOT NULL,
    hotel_phone character varying(255) NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_rate character varying(255) NOT NULL,
    hotel_region character varying(255) NOT NULL,
    hotel_service boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_wifi boolean NOT NULL
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    26932    hotels_id_seq    SEQUENCE     v   CREATE SEQUENCE public.hotels_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.hotels_id_seq;
       public          postgres    false    218                       0    0    hotels_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.hotels_id_seq OWNED BY public.hotels.id;
          public          postgres    false    217            �            1259    26957    pensions    TABLE     /  CREATE TABLE public.pensions (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    pension_name smallint NOT NULL,
    CONSTRAINT pensions_pension_name_check CHECK (((pension_name >= 0) AND (pension_name <= 6)))
);
    DROP TABLE public.pensions;
       public         heap    postgres    false            �            1259    26956    pensions_id_seq    SEQUENCE     x   CREATE SEQUENCE public.pensions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.pensions_id_seq;
       public          postgres    false    223                       0    0    pensions_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.pensions_id_seq OWNED BY public.pensions.id;
          public          postgres    false    222            �            1259    26942    sessions    TABLE       CREATE TABLE public.sessions (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    session_fnshdate date NOT NULL,
    session_strtdate date NOT NULL,
    session_hotel_id bigint
);
    DROP TABLE public.sessions;
       public         heap    postgres    false            �            1259    26941    sessions_id_seq    SEQUENCE     x   CREATE SEQUENCE public.sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.sessions_id_seq;
       public          postgres    false    220                       0    0    sessions_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;
          public          postgres    false    219            �            1259    26923    users    TABLE     �  CREATE TABLE public.users (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    user_role character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL,
    user_password character varying(255) NOT NULL,
    CONSTRAINT users_user_role_check CHECK (((user_role)::text = ANY ((ARRAY['ADMIN'::character varying, 'EMPLOYEE'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    26922    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            d           2604    26936 	   hotels id    DEFAULT     f   ALTER TABLE ONLY public.hotels ALTER COLUMN id SET DEFAULT nextval('public.hotels_id_seq'::regclass);
 8   ALTER TABLE public.hotels ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            f           2604    26960    pensions id    DEFAULT     j   ALTER TABLE ONLY public.pensions ALTER COLUMN id SET DEFAULT nextval('public.pensions_id_seq'::regclass);
 :   ALTER TABLE public.pensions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            e           2604    26945    sessions id    DEFAULT     j   ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);
 :   ALTER TABLE public.sessions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            c           2604    26926    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            	          0    26953    hotel2pensions 
   TABLE DATA           \   COPY public.hotel2pensions (hotel2pensions_hotel_id, hotel2pensions_pension_id) FROM stdin;
    public          postgres    false    221   �,                 0    26933    hotels 
   TABLE DATA           �   COPY public.hotels (id, created_at, updated_at, hotel_address, hotel_city, hotel_email, hotel_fitness, hotel_name, hotel_otopark, hotel_phone, hotel_pool, hotel_rate, hotel_region, hotel_service, hotel_spa, hotel_wifi) FROM stdin;
    public          postgres    false    218   -                 0    26957    pensions 
   TABLE DATA           L   COPY public.pensions (id, created_at, updated_at, pension_name) FROM stdin;
    public          postgres    false    223   *-                 0    26942    sessions 
   TABLE DATA           t   COPY public.sessions (id, created_at, updated_at, session_fnshdate, session_strtdate, session_hotel_id) FROM stdin;
    public          postgres    false    220   y-                 0    26923    users 
   TABLE DATA           `   COPY public.users (id, created_at, updated_at, user_role, user_name, user_password) FROM stdin;
    public          postgres    false    216   �-                  0    0    hotels_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.hotels_id_seq', 1, false);
          public          postgres    false    217                       0    0    pensions_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pensions_id_seq', 2, true);
          public          postgres    false    222                       0    0    sessions_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.sessions_id_seq', 1, false);
          public          postgres    false    219                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 3, true);
          public          postgres    false    215            l           2606    26940    hotels hotels_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    218            p           2606    26963    pensions pensions_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pensions
    ADD CONSTRAINT pensions_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pensions DROP CONSTRAINT pensions_pkey;
       public            postgres    false    223            n           2606    26947    sessions sessions_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.sessions DROP CONSTRAINT sessions_pkey;
       public            postgres    false    220            j           2606    26931    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            q           2606    26948 $   sessions fk769s4r4x9maqdv1x3fkkxvor5    FK CONSTRAINT     �   ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT fk769s4r4x9maqdv1x3fkkxvor5 FOREIGN KEY (session_hotel_id) REFERENCES public.hotels(id);
 N   ALTER TABLE ONLY public.sessions DROP CONSTRAINT fk769s4r4x9maqdv1x3fkkxvor5;
       public          postgres    false    220    218    4716            r           2606    26964 *   hotel2pensions fka08uis8ccbwp1pr4pymkh1tv8    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel2pensions
    ADD CONSTRAINT fka08uis8ccbwp1pr4pymkh1tv8 FOREIGN KEY (hotel2pensions_pension_id) REFERENCES public.pensions(id);
 T   ALTER TABLE ONLY public.hotel2pensions DROP CONSTRAINT fka08uis8ccbwp1pr4pymkh1tv8;
       public          postgres    false    4720    221    223            s           2606    26969 *   hotel2pensions fkojrae8t0a5dd4nxy28r9v9bps    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel2pensions
    ADD CONSTRAINT fkojrae8t0a5dd4nxy28r9v9bps FOREIGN KEY (hotel2pensions_hotel_id) REFERENCES public.hotels(id);
 T   ALTER TABLE ONLY public.hotel2pensions DROP CONSTRAINT fkojrae8t0a5dd4nxy28r9v9bps;
       public          postgres    false    221    4716    218            	      x������ � �            x������ � �         ?   x�}ɱ� �:�"����ga�9���o�O �!Z̗��B:;(�
G������m7�T�'            x������ � �         K   x�3�4202�50�52Q0��26 "=SKC3s|R�.��~���@�e������R��>�����II@����� �X�     