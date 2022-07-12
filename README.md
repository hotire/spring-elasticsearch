# Spring ElasticSearch

![elastic](doc/logo.png)

### Elastic 특징 

분산 검색 엔진

관계형 데이터베이스 | elasticsearch
|:---:|:---:|
Database | Index
Table	| Type
Row	| Document
Column |	Field
Schema	| Mapping
Index	| Everything is indexed
SQL	| Query DSL


데이터 모델을 JSON으로 사용하고 있어서 요청과 응답을 모두 JSON 문서로 주고 받는다.
스키마를 미리 정의하지 않아도 JSON 문서를 넘겨주면 자동으로 인덱싱한다.


# Elastic 

### Elastic Glossary

- https://www.elastic.co/guide/en/elastic-stack-glossary/current/terms.html

## Algorithm

### TF-IDF

TF-IDF(Term Frequency - Inverse Document Frequency)는 정보 검색과 텍스트 마이닝에서 이용하는 가중치로, 여러 문서로 이루어진 문서군이 있을 때 어떤 단어가 특정 문서 내에서 얼마나 중요한 것인지를 나타내는 통계적 수치이다. 
문서의 핵심어를 추출하거나, 검색 엔진에서 검색 결과의 순위를 결정하거나, 문서들 사이의 비슷한 정도를 구하는 등의 용도로 사용할 수 있다.


### References
- https://www.elastic.co/guide/index.html
- https://esbook.kimjmin.net/
