PGDMP                  
    |            restaurante    15.8    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16526    restaurante    DATABASE     �   CREATE DATABASE restaurante WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE restaurante;
                postgres    false            �            1259    16580    item    TABLE     �   CREATE TABLE public.item (
    id integer NOT NULL,
    nome character varying(255),
    price integer,
    title character varying(255),
    image character varying(255)
);
    DROP TABLE public.item;
       public         heap    postgres    false            �          0    16580    item 
   TABLE DATA           =   COPY public.item (id, nome, price, title, image) FROM stdin;
    public          postgres    false    214   q       e           2606    16586    item item_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.item DROP CONSTRAINT item_pkey;
       public            postgres    false    214            �   �  x����n�0�����w���M'!"�֕��iA���N���v���#�{1ܒ�4�����}�?�Z�S<I1SXZA86��ӺWW*�5'״v�h�RB��)�z��G��0)ØP�!���1	p<e%.݊A7��>B�w�q�}m{5e���;���eg�O�f�CN=^c�x���y�J2:{O�G���f�p7ë��h�.X��� �y����}k%��8)��Bb�G����em�D�[1{�[?�=��>l>��bz�q�[�`���Έ��a�b�+�/�wlPp��6���7S=�yEP�4#����-S�lD�s桕↊ɆIʬ �*��O�����.fQ
Q���
 
 9m�����F���5���������zq�.�(^��T$r�>���(�?�%��l9�0�8�m��$�n     