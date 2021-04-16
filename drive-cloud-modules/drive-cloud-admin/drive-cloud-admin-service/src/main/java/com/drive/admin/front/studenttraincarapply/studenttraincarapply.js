import request from '@/utils/request'


// 分页查询学员学车预约表列表
export function listPageStudentTrainCarApply(query) {
  return request({
    url: '/admin/studentTrainCarApply/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询学员学车预约表列表
export function listStudentTrainCarApply(query) {
  return request({
    url: '/admin/studentTrainCarApply/findList',
    method: 'post',
    data: query
  })
}

// 查询学员学车预约表详细
export function getStudentTrainCarApply(trainApplyNo) {
  return request({
    url: '/admin/studentTrainCarApply/' + trainApplyNo,
    method: 'get'
  })
}

// 新增学员学车预约表
export function addStudentTrainCarApply(data) {
  return request({
    url: '/admin/studentTrainCarApply',
    method: 'post',
    data: data
  })
}

// 修改学员学车预约表
export function updateStudentTrainCarApply(data) {
  return request({
    url: '/admin/studentTrainCarApply',
    method: 'put',
    data: data
  })
}

// 删除学员学车预约表
export function delStudentTrainCarApply(trainApplyNo) {
  return request({
    url: '/admin/studentTrainCarApply/' + trainApplyNo,
    method: 'delete'
  })
}


// 状态启用/停用学员学车预约表
export function changeStatus(data) {
  return request({
    url: '/admin/studentTrainCarApply/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员学车预约表
export function exportStudentTrainCarApply(query) {
  return request({
    url: '/admin/studentTrainCarApply/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}