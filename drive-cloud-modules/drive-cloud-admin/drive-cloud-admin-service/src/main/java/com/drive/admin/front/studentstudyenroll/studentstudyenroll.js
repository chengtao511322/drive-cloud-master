import request from '@/utils/request'

                                                                                                
// 分页查询学员学车报名单列表
export function listPageStudentStudyEnroll(query) {
  return request({
    url: '/admin/studentStudyEnroll/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学员学车报名单列表
export function listStudentStudyEnroll(query) {
  return request({
    url: '/admin/studentStudyEnroll/findList',
    method: 'post',
    params: query
  })
}

// 查询学员学车报名单详细
export function getStudentStudyEnroll(studyEnrollNo) {
  return request({
    url: '/admin/studentStudyEnroll/' + studyEnrollNo,
    method: 'get'
  })
}

// 新增学员学车报名单
export function addStudentStudyEnroll(data) {
  return request({
    url: '/admin/studentStudyEnroll',
    method: 'post',
    data: data
  })
}

// 修改学员学车报名单
export function updateStudentStudyEnroll(data) {
  return request({
    url: '/admin/studentStudyEnroll',
    method: 'put',
    data: data
  })
}

// 删除学员学车报名单
export function delStudentStudyEnroll(studyEnrollNo) {
  return request({
    url: '/admin/studentStudyEnroll/' + studyEnrollNo,
    method: 'delete'
  })
}


// 状态启用/停用学员学车报名单
export function changeStatus(data) {
  return request({
    url: '/admin/studentStudyEnroll/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员学车报名单
export function exportStudentStudyEnroll(query) {
  return request({
    url: '/admin/studentStudyEnroll/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}