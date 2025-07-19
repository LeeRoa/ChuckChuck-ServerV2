CREATE TABLE cc_code (
                         code_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '프로젝트 DB 관리 코드 번호',
                         code_type VARCHAR(50) NOT NULL COMMENT '코드 그룹 타입 (예: ROLE, DEPARTMENT 등)',
                         code_name VARCHAR(100) NOT NULL COMMENT '상세한 코드 번호의 이름',
                         display_order INT NOT NULL COMMENT '코드 타입 간의 뷰단 정렬 순서',
                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '코드 등록일',
                         updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '코드 수정일',
                         spare VARCHAR(255) COMMENT '예비 컬럼 (향후 확장용)',
                         UNIQUE KEY uniq_code_type_name (code_type, code_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='프로젝트의 공통 코드를 관리하는 테이블';

CREATE TABLE cc_department (
                               department_id BIGINT PRIMARY KEY COMMENT '부서 고유 ID',
                               parent_department_id BIGINT DEFAULT NULL COMMENT '상위 부서 ID (NULL = 최상위)',
                               department_name VARCHAR(100) NOT NULL UNIQUE COMMENT '부서 이름',
                               display_order INT NOT NULL DEFAULT 0 COMMENT '정렬 순서',
                               is_active CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '활성화 여부',
                               created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
                               updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
                               spare VARCHAR(255) COMMENT '예비 컬럼',

                               CONSTRAINT fk_parent_dept FOREIGN KEY (parent_department_id) REFERENCES cc_department(department_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='부서 계층 구조 관리 테이블';

CREATE TABLE cc_rank (
                         rank_id BIGINT PRIMARY KEY COMMENT '직급 고유 ID',
                         parent_rank_id BIGINT DEFAULT NULL COMMENT '상위 직급 ID',
                         rank_name VARCHAR(100) NOT NULL UNIQUE COMMENT '직급 명',
                         display_order INT NOT NULL DEFAULT 0 COMMENT '계층 정렬 순서',
                         is_active CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '직급 사용 여부',
                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
                         updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
                         spare VARCHAR(255),
                         CONSTRAINT fk_parent_rank FOREIGN KEY (parent_rank_id) REFERENCES cc_rank(rank_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='직급 계층 관리 테이블';

CREATE TABLE cc_company (
                            company_no BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회사 고유 번호',
                            biz_no VARCHAR(20) NOT NULL UNIQUE COMMENT '사업자 등록 번호',
                            company_name VARCHAR(100) NOT NULL COMMENT '회사 명',
                            company_address VARCHAR(255) NOT NULL COMMENT '회사 주소',
                            company_call VARCHAR(20) NOT NULL COMMENT '회사 전화번호',

                            created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자',
                            updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일자',

                            occur_leave_dt DATE NOT NULL COMMENT '연차 발생 기준일',
                            remain_leave_whether CHAR(1) NOT NULL COMMENT '잔여 연차 이월 여부',
                            default_leave_cnt INT NOT NULL COMMENT '기본 지급 연차 일 수',

                            basic_work_hours INT NOT NULL COMMENT '주 기본 근무 시간 (단위: 시간)',
                            overtime_pay_whether CHAR(1) NOT NULL COMMENT '초과 근무 수당 지급 여부',

                            work_type BIGINT NOT NULL COMMENT '기본 근무 유형 코드 (cc_code.code_id 참조)',

                            spare VARCHAR(255) COMMENT '예비 필드',

                            CONSTRAINT fk_company_work_type FOREIGN KEY (work_type)
                                REFERENCES cc_code(code_id)
);

CREATE TABLE cc_employee (
                             employee_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사원 고유 번호',

                             email VARCHAR(100) NOT NULL UNIQUE COMMENT '이메일 (로그인 아이디)',
                             phone_number VARCHAR(15) UNIQUE COMMENT '전화번호 (예: 010-1234-5678)',
                             password_hash CHAR(64) NOT NULL COMMENT '비밀번호 SHA-256 해시',

                             full_name VARCHAR(50) NOT NULL COMMENT '사원 이름',
                             birth_date DATE NOT NULL COMMENT '생년월일',

                             is_active CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '계정 활성 여부 (Y/N)',

                             agree_privacy CHAR(1) NOT NULL DEFAULT 'N' COMMENT '개인정보 수집 동의 여부 (Y/N)',

                             position_code VARCHAR(20) COMMENT '직책 코드 또는 명칭',
                             login_fail_count INT NOT NULL DEFAULT 0 COMMENT '로그인 실패 횟수',

                             join_dt DATETIME DEFAULT NULL COMMENT '입사일',
                             retire_dt DATETIME DEFAULT NULL COMMENT '퇴사일',

                             role_id BIGINT NOT NULL COMMENT '권한 코드 ID (cc_code: ROLE)',
                             department_id BIGINT DEFAULT NULL COMMENT '부서 ID',
                             rank_id BIGINT DEFAULT NULL COMMENT '직급 ID',
                             company_no VARCHAR(20) DEFAULT NULL COMMENT '회사 고유번호 (사업자 번호)',

                             manager_id BIGINT DEFAULT NULL COMMENT '직속 상사 사원번호 (자기참조)',
                             work_type BIGINT DEFAULT NULL COMMENT '근무 유형 코드 ID (cc_code: 근무 유형)',
                             basic_work_hours INT DEFAULT NULL COMMENT '주 기본 근무 시간 (단위: 시간)',

                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '계정 생성일자',
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '계정 수정일자',

                             spare VARCHAR(255) COMMENT '예비 필드',

    -- FK 제약
                             CONSTRAINT fk_employee_role FOREIGN KEY (role_id)
                                 REFERENCES cc_code(code_id),

                             CONSTRAINT fk_employee_department FOREIGN KEY (department_id)
                                 REFERENCES cc_department(department_id),

                             CONSTRAINT fk_employee_rank FOREIGN KEY (rank_id)
                                 REFERENCES cc_rank(rank_id),

                             CONSTRAINT fk_employee_company FOREIGN KEY (company_no)
                                 REFERENCES cc_company(biz_no),

                             CONSTRAINT fk_employee_manager FOREIGN KEY (manager_id)
                                 REFERENCES cc_employee(employee_id),

                             CONSTRAINT fk_employee_work_type FOREIGN KEY (work_type)
                                 REFERENCES cc_code(code_id)
);

CREATE TABLE cc_file (
                         file_no BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '첨부파일 고유 번호',

                         origin_name VARCHAR(255) NOT NULL COMMENT '원본 파일명',
                         stored_name VARCHAR(255) DEFAULT NULL COMMENT '저장된 파일명 (UUID 등으로 변경된 이름)',

                         file_size INT NOT NULL COMMENT '파일 크기 (byte 단위)',
                         file_path VARCHAR(500) NOT NULL COMMENT '파일 저장 경로',
                         file_content_type VARCHAR(100) NOT NULL COMMENT '파일 MIME 타입 (ex: image/png, application/pdf)',

                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '파일 등록일시',
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '파일 수정일시',

                         spare VARCHAR(255) COMMENT '예비 필드'
);

CREATE TABLE file_mapping (
                              file_mapping_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '파일 매핑 ID',

                              file_no BIGINT NOT NULL COMMENT '첨부파일 번호 (cc_files FK)',
                              target_type CHAR(1) NOT NULL COMMENT '파일 유형 (A: 프로필, B: 공지, C: 결재 등)',
                              target_id BIGINT NOT NULL COMMENT '파일이 속한 대상 객체의 ID (예: emp_id, notice_no, doc_no)',

                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '매핑 생성일시',
                              spare VARCHAR(255),

                              CONSTRAINT fk_file_mapping_file FOREIGN KEY (file_no)
                                  REFERENCES cc_file(file_no)
);

CREATE TABLE leave_policy (
                              leave_policy_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '휴가 정책 ID',

                              leave_type BIGINT NOT NULL COMMENT '휴가 유형 코드 ID (cc_code.code_type = leave_type)',
                              leave_provide_days INT NOT NULL COMMENT '기본 제공 휴가 일수',

                              role_id BIGINT DEFAULT NULL COMMENT '적용 대상 권한 (예: ROLE_EMPLOYEE)',
                              rank_id BIGINT DEFAULT NULL COMMENT '적용 대상 직급 ID (예: 과장, 부장)',
                              company_no VARCHAR(20) DEFAULT NULL COMMENT '적용 회사 번호 (멀티 테넌시 대응)',

                              effective_start DATE DEFAULT NULL COMMENT '정책 적용 시작일',
                              effective_end DATE DEFAULT NULL COMMENT '정책 적용 종료일 (NULL이면 무기한)',

                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '정책 생성일시',
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '정책 수정일시',

                              spare VARCHAR(255) COMMENT '예비 필드',

    -- FK 제약
                              CONSTRAINT fk_leave_policy_type FOREIGN KEY (leave_type)
                                  REFERENCES cc_code(code_id),

                              CONSTRAINT fk_leave_policy_role FOREIGN KEY (role_id)
                                  REFERENCES cc_code(code_id),

                              CONSTRAINT fk_leave_policy_rank FOREIGN KEY (rank_id)
                                  REFERENCES cc_rank(rank_id),

                              CONSTRAINT fk_leave_policy_company FOREIGN KEY (company_no)
                                  REFERENCES cc_company(biz_no)
);

CREATE TABLE emp_commute (
                             emp_commute_seq BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '근태 관리 시퀀스',

                             employee_id BIGINT NOT NULL COMMENT '사원 고유 번호',

                             work_date DATE NOT NULL COMMENT '출근 일자',
                             attendance_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '출근 시간',
                             leave_work_time TIMESTAMP DEFAULT NULL COMMENT '퇴근 시간',

                             commute_status_code BIGINT NOT NULL COMMENT '근태 상태 코드 (cc_code.code_type = commute_status)',

                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',

                             spare VARCHAR(255) COMMENT '예비 필드',

                             CONSTRAINT fk_commute_employee FOREIGN KEY (employee_id)
                                 REFERENCES cc_employee(employee_id),

                             CONSTRAINT fk_commute_status FOREIGN KEY (commute_status_code)
                                 REFERENCES cc_code(code_id)
);

CREATE TABLE cc_approval (
                             doc_no BIGINT PRIMARY KEY COMMENT '결재 문서의 고유 번호',
                             doc_type BIGINT NOT NULL COMMENT '문서 타입 코드 (cc_code 테이블 PK)',
                             creator_id BIGINT NOT NULL COMMENT '문서 작성자 사원번호 (cc_employee 테이블 PK)',
                             retention_period TIMESTAMP DEFAULT NULL COMMENT '문서 보존 연한, NULL = 영구 보관',
                             doc_title VARCHAR(200) NOT NULL COMMENT '문서 제목',
                             doc_content TEXT NOT NULL COMMENT '문서 내용',
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '문서 작성일시',
                             approval_deadline DATETIME DEFAULT NULL COMMENT '결재 마감 기한 (NULL = 없음)',
                             draft_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '임시 저장 여부',
                             delete_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
                             total_line_count INT NOT NULL DEFAULT 0 COMMENT '총 결재 라인 수',
                             current_appro_step INT DEFAULT NULL COMMENT '현재 결재 단계 (NULL = 결재 없음)',
                             spare VARCHAR(255) COMMENT '예비 컬럼',

                             CONSTRAINT fk_approval_doc_type FOREIGN KEY (doc_type) REFERENCES cc_code(code_id),
                             CONSTRAINT fk_approval_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='전자결재 문서 관리 테이블';

CREATE TABLE approval_line (
                               line_id BIGINT PRIMARY KEY COMMENT '결재 라인 고유 번호',
                               line_type BIGINT NOT NULL COMMENT '라인 유형 코드 (결재/합의/참조 등, cc_code 참조)',
                               doc_no BIGINT NOT NULL COMMENT '결재 문서 번호 (cc_approval PK)',
                               approver_id BIGINT NOT NULL COMMENT '결재자 사원 번호 (cc_employee PK)',
                               status_code BIGINT DEFAULT NULL COMMENT '결재 상태 코드 (cc_code PK), NULL = 결재 전',
                               approval_seq INT DEFAULT NULL COMMENT '결재 순서 (NULL = 통보/참조)',
                               approval_dt DATETIME DEFAULT NULL COMMENT '결재 일시 (NULL = 결재 전)',
                               approval_comment TEXT DEFAULT NULL COMMENT '결재 의견 내용 (NULL = 미작성)',
                               comment_dt DATETIME DEFAULT NULL COMMENT '의견 작성 시각 (NULL = 미작성)',
                               spare VARCHAR(255) COMMENT '예비 컬럼',

                               CONSTRAINT fk_line_type FOREIGN KEY (line_type) REFERENCES cc_code(code_id),
                               CONSTRAINT fk_line_doc FOREIGN KEY (doc_no) REFERENCES cc_approval(doc_no),
                               CONSTRAINT fk_line_approver FOREIGN KEY (approver_id) REFERENCES cc_employee(employee_id),
                               CONSTRAINT fk_line_status_code FOREIGN KEY (status_code) REFERENCES cc_code(code_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='전자결재 결재자 상태 관리 테이블';

CREATE TABLE approval_line_system (
                                      lineset_no BIGINT PRIMARY KEY COMMENT '결재라인 설정의 고유 번호',
                                      line_type BIGINT NOT NULL COMMENT '결재, 합의, 참조 등 결재라인 유형',
                                      doc_type BIGINT NOT NULL COMMENT '문서 유형 코드',
                                      creator_id BIGINT NOT NULL COMMENT '결재라인 생성자 사원번호',
                                      approver_id BIGINT NOT NULL COMMENT '결재자 사원번호',
                                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '결재라인 생성 시각',
                                      updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '결재라인 수정 시각',
                                      delete_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (논리삭제 플래그)',
                                      approval_seq INT DEFAULT NULL COMMENT '결재 순서 (NULL = 통보/참조)',
                                      spare VARCHAR(255) COMMENT '예비 컬럼',

                                      CONSTRAINT fk_line_type_2 FOREIGN KEY (line_type) REFERENCES cc_code(code_id),
                                      CONSTRAINT fk_doc_type FOREIGN KEY (doc_type) REFERENCES cc_code(code_id),
                                      CONSTRAINT fk_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id),
                                      CONSTRAINT fk_approver FOREIGN KEY (approver_id) REFERENCES cc_employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사원의 결재라인 설정 관리 테이블';

CREATE TABLE department_leader (
                                   leader_id BIGINT PRIMARY KEY COMMENT '부서 팀장 고유 ID',
                                   department_id BIGINT NOT NULL COMMENT '부서 고유 ID (cc_department 참조)',
                                   employee_id BIGINT NOT NULL COMMENT '팀장 사원번호 (cc_employee 참조)',
                                   role_type BIGINT DEFAULT NULL COMMENT '팀장 역할 코드 (cc_code 참조, 팀장/부팀장 등)',
                                   assigned_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '부서장 지정일',
                                   spare VARCHAR(255) COMMENT '예비 컬럼',

                                   CONSTRAINT fk_leader_department FOREIGN KEY (department_id) REFERENCES cc_department(department_id),
                                   CONSTRAINT fk_leader_employee FOREIGN KEY (employee_id) REFERENCES cc_employee(employee_id),
                                   CONSTRAINT fk_leader_role FOREIGN KEY (role_type) REFERENCES cc_code(code_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='부서 팀장 관리 테이블';

CREATE TABLE schedule_group (
                                schedule_group_id BIGINT PRIMARY KEY COMMENT '일정 그룹 고유 ID',
                                schedule_group_name VARCHAR(100) NOT NULL COMMENT '일정 그룹 이름',
                                schedule_group_description VARCHAR(255),
                                created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '그룹 생성일시',
                                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '그룹 수정일시',
                                creator_id BIGINT NOT NULL COMMENT '그룹 생성자 사번',
                                spare VARCHAR(255),
                                CONSTRAINT fk_creator_schedule_group FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='일정 그룹 테이블';

CREATE TABLE cc_schedule (
                             schedule_id BIGINT PRIMARY KEY COMMENT '일정 고유 ID',
                             schedule_name VARCHAR(100) NOT NULL COMMENT '일정 제목',
                             schedule_content TEXT COMMENT '일정 내용',
                             schedule_start_dt DATETIME NOT NULL COMMENT '일정 시작 일시',
                             schedule_end_dt DATETIME NOT NULL COMMENT '일정 종료 일시',
                             schedule_place VARCHAR(255),
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일시',
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
                             allday_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '종일 여부',
                             schedule_group_id BIGINT NOT NULL COMMENT '일정 그룹 ID',
                             creator_id BIGINT NOT NULL COMMENT '일정 생성자',
                             schedule_type BIGINT NOT NULL COMMENT '일정 유형',
                             status BIGINT NOT NULL COMMENT '일정 상태',
                             spare VARCHAR(255),
                             CONSTRAINT fk_schedule_group FOREIGN KEY (schedule_group_id) REFERENCES schedule_group(schedule_group_id),
                             CONSTRAINT fk_schedule_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id),
                             CONSTRAINT fk_schedule_type FOREIGN KEY (schedule_type) REFERENCES cc_code(code_id),
                             CONSTRAINT fk_schedule_status FOREIGN KEY (status) REFERENCES cc_code(code_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='일정 테이블';

CREATE TABLE schedule_employee (
                                   col_seq BIGINT PRIMARY KEY COMMENT '시퀀스',
                                   schedule_id BIGINT NOT NULL COMMENT '일정 ID',
                                   role_type BIGINT NOT NULL COMMENT '참여자 역할',
                                   spare VARCHAR(255),
                                   CONSTRAINT fk_schedule_emp_schedule FOREIGN KEY (schedule_id) REFERENCES cc_schedule(schedule_id),
                                   CONSTRAINT fk_schedule_emp_role FOREIGN KEY (role_type) REFERENCES cc_code(code_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='일정-사원 연결 테이블';

CREATE TABLE schedule_group_member (
                                       group_member_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 그룹-사원 연결 고유 ID (시퀀스)',

                                       schedule_group_id BIGINT NOT NULL COMMENT '일정그룹 고유ID',
                                       employee_id BIGINT NOT NULL COMMENT '사원번호',

                                       spare VARCHAR(255) COMMENT '예비 필드',

    -- 외래키 제약조건
                                       CONSTRAINT fk_sgm_schedule_group FOREIGN KEY (schedule_group_id)
                                           REFERENCES schedule_group(schedule_group_id),

                                       CONSTRAINT fk_sgm_employee FOREIGN KEY (employee_id)
                                           REFERENCES cc_employee(employee_id),

    -- 유니크 제약조건: 동일 그룹에 동일 사원 중복 방지
                                       CONSTRAINT uq_sgm_group_employee UNIQUE (schedule_group_id, employee_id)
);

CREATE TABLE cc_notice (
                           notice_no BIGINT PRIMARY KEY COMMENT '공지사항 고유 번호',
                           notice_title VARCHAR(200) NOT NULL COMMENT '공지 제목',
                           notice_content TEXT NOT NULL COMMENT '공지 내용',
                           allow_comment CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '댓글 허용 여부',
                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
                           updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
                           notification_yn CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '알림 여부',
                           delete_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
                           view_count INT NOT NULL DEFAULT 0 COMMENT '조회수',
                           creator_id BIGINT NOT NULL COMMENT '작성자 사번',
                           spare VARCHAR(255),
                           CONSTRAINT fk_notice_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='공지사항 테이블';

CREATE TABLE work_policy (
                             work_policy_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '근무 정책 고유ID',

                             day_of_week VARCHAR(10) NOT NULL COMMENT '요일',
                             work_yn CHAR(1) NOT NULL COMMENT '근무 여부 (Y/N)',

                             work_start_time TIME DEFAULT NULL COMMENT '근무 시작 시간',
                             work_end_time TIME DEFAULT NULL COMMENT '근무 종료 시간',

                             lunch_start_time TIME NOT NULL COMMENT '점심 시작 시간',
                             lunch_end_time TIME NOT NULL COMMENT '점심 종료 시간',

                             employee_id BIGINT NOT NULL COMMENT '사원번호',

                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',

                             spare VARCHAR(255) COMMENT '예비 필드',

    -- 외래키 제약
                             CONSTRAINT fk_work_policy_employee FOREIGN KEY (employee_id)
                                 REFERENCES cc_employee(employee_id),

    -- 유니크 제약: 동일 사원 + 요일 중복 방지
                             CONSTRAINT uq_work_policy_employee_day UNIQUE (employee_id, day_of_week)
);

CREATE TABLE related_doc (
                             related_no BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '관련문서의 고유번호',

                             approval_doc_no BIGINT NOT NULL COMMENT '기준 결재 문서 번호 (cc_approval의 PK)',
                             related_doc_no BIGINT NOT NULL COMMENT '관련 결재 문서 번호 (cc_approval의 PK)',

                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일시',
                             spare VARCHAR(255) COMMENT '예비 필드',

    -- 외래키 제약
                             CONSTRAINT fk_related_doc_main FOREIGN KEY (approval_doc_no)
                                 REFERENCES cc_approval(doc_no)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE,

                             CONSTRAINT fk_related_doc_sub FOREIGN KEY (related_doc_no)
                                 REFERENCES cc_approval(doc_no)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE

    -- 자기 자신 참조 방지
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='결재 문서 간 관련 관계 관리';

CREATE TABLE emp_leave (
                           leave_seq BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '휴가 관리 시퀀스',

                           leave_type BIGINT NOT NULL COMMENT '휴가 유형 코드 (cc_code.code_type = leave_type)',
                           leave_use_days INT NOT NULL COMMENT '휴가 사용 일수',

                           leave_start_dt DATETIME NOT NULL COMMENT '휴가 시작일',
                           leave_end_dt DATETIME NOT NULL COMMENT '휴가 종료일',

                           employee_id BIGINT NOT NULL COMMENT '사원 고유 번호',
                           doc_no BIGINT NOT NULL COMMENT '전자결재 문서 번호',

                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',

                           CONSTRAINT fk_leave_employee FOREIGN KEY (employee_id)
                               REFERENCES cc_employee(employee_id),

                           CONSTRAINT fk_leave_type FOREIGN KEY (leave_type)
                               REFERENCES cc_code(code_id),

                           CONSTRAINT fk_leave_doc FOREIGN KEY (doc_no)
                               REFERENCES cc_approval(doc_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='휴가 사용 기록 테이블';

CREATE TABLE approval_template (
                                   tem_no BIGINT PRIMARY KEY COMMENT '결재 템플릿 고유 번호',
                                   doc_type BIGINT NOT NULL COMMENT '문서 유형 코드 (cc_code PK)',
                                   creator_id BIGINT NOT NULL COMMENT '템플릿 생성자 사원번호 (cc_employee PK)',
                                   doc_title VARCHAR(200) NOT NULL COMMENT '템플릿 제목',
                                   doc_content TEXT NOT NULL COMMENT '템플릿 본문',
                                   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '템플릿 생성 시각',
                                   updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '템플릿 수정 시각',
                                   delete_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (논리삭제)',
                                   view_level BIGINT NOT NULL COMMENT '템플릿 공유 레벨 코드 (cc_code PK)',
                                   spare VARCHAR(255) COMMENT '예비 컬럼',

                                   CONSTRAINT fk_template_doc_type FOREIGN KEY (doc_type) REFERENCES cc_code(code_id),
                                   CONSTRAINT fk_template_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id),
                                   CONSTRAINT fk_template_view_level FOREIGN KEY (view_level) REFERENCES cc_code(code_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='결재 문서 템플릿 관리 테이블';

CREATE TABLE notice_comment (
                                comment_id BIGINT PRIMARY KEY COMMENT '댓글 고유 번호',
                                notice_no BIGINT NOT NULL COMMENT '공지사항 번호',
                                content TEXT NOT NULL COMMENT '댓글 내용',
                                created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시각',
                                updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
                                delete_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
                                creator_id BIGINT NOT NULL COMMENT '댓글 작성자',
                                spare VARCHAR(255),
                                CONSTRAINT fk_comment_notice FOREIGN KEY (notice_no) REFERENCES cc_notice(notice_no),
                                CONSTRAINT fk_comment_creator FOREIGN KEY (creator_id) REFERENCES cc_employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='공지사항 댓글 테이블';

CREATE TABLE alert_master (
                              alert_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '알림 유형 ID',

                              alert_type BIGINT NOT NULL COMMENT '알림 타입 코드 ID (cc_code.code_type = alert_type)',
                              alert_content VARCHAR(500) NOT NULL COMMENT '알림 내용',
                              send_type BIGINT NOT NULL COMMENT '발송 타입 코드 ID (cc_code.code_type = send_type)',
                              enabled_yn CHAR(1) NOT NULL COMMENT '사용 여부 (Y/N)',
                              alert_template TEXT DEFAULT NULL COMMENT '알림 템플릿 (NULL이면 템플릿 없음)',
                              view_level BIGINT NOT NULL COMMENT '알림 표시 등급 (cc_rank.rank_id 참조)',

                              creator_id BIGINT NOT NULL COMMENT '알림 등록자 ID (cc_employee.employee_id)',
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',

                              spare VARCHAR(255) COMMENT '예비 필드',

    -- FK 제약
                              CONSTRAINT fk_alert_type FOREIGN KEY (alert_type)
                                  REFERENCES cc_code(code_id),

                              CONSTRAINT fk_alert_send_type FOREIGN KEY (send_type)
                                  REFERENCES cc_code(code_id),

                              CONSTRAINT fk_alert_creator FOREIGN KEY (creator_id)
                                  REFERENCES cc_employee(employee_id),

                              CONSTRAINT fk_alert_view_level FOREIGN KEY (view_level)
                                  REFERENCES cc_rank(rank_id)
);

CREATE TABLE alert_instance (
                                alert_instance_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '알림 발송 고유 ID',

                                alert_id BIGINT NOT NULL COMMENT '알림 유형 ID (alert_master.alert_id 참조)',
                                recipient_id BIGINT NOT NULL COMMENT '수신자 ID (cc_employee.employee_id)',
                                send_id BIGINT NOT NULL COMMENT '발송자 ID (cc_employee.employee_id)',
                                alert_title VARCHAR(500) NOT NULL COMMENT '알림 제목 (템플릿 적용 결과)',

                                is_read CHAR(1) NOT NULL DEFAULT 'N' COMMENT '읽음 여부 (Y/N)',
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '발송일시',

                                spare VARCHAR(255) COMMENT '예비 필드',

    -- FK 제약
                                CONSTRAINT fk_alert_instance_alert FOREIGN KEY (alert_id)
                                    REFERENCES alert_master(alert_id),

                                CONSTRAINT fk_alert_instance_recipient FOREIGN KEY (recipient_id)
                                    REFERENCES cc_employee(employee_id),

                                CONSTRAINT fk_alert_instance_sender FOREIGN KEY (send_id)
                                    REFERENCES cc_employee(employee_id)
);

CREATE TABLE account_request (
                                 request_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '가입 요청 고유 ID',

                                 requester_id BIGINT NOT NULL COMMENT '가입 요청자 ID (cc_employee.employee_id)',
                                 requested_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '요청 일시',

                                 processed_at DATETIME DEFAULT NULL COMMENT '처리 일시 (NULL이면 미처리 상태)',
                                 processed_by BIGINT DEFAULT NULL COMMENT '처리 관리자 ID (cc_employee.employee_id)',

                                 request_status BIGINT NOT NULL COMMENT '요청 상태 코드 (cc_code.code_type = request_status)',
                                 reject_reason TEXT DEFAULT NULL COMMENT '거절 사유 (거절 시 입력)',

                                 spare VARCHAR(255) COMMENT '예비 필드',

    -- FK 제약
                                 CONSTRAINT fk_account_requester FOREIGN KEY (requester_id)
                                     REFERENCES cc_employee(employee_id),

                                 CONSTRAINT fk_account_processed_by FOREIGN KEY (processed_by)
                                     REFERENCES cc_employee(employee_id),

                                 CONSTRAINT fk_account_status FOREIGN KEY (request_status)
                                     REFERENCES cc_code(code_id)
);

show tables;

commit;