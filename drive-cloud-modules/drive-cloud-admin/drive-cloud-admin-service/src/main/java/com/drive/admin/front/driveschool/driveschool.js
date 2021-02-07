import request from '@/utils/request'

                                            
// 分页查询平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。列表
export function listPageDriveSchool(query) {
  return request({
    url: '/admin/driveSchool/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。列表
export function listDriveSchool(query) {
  return request({
    url: '/admin/driveSchool/findList',
    method: 'post',
    params: query
  })
}

// 查询平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。详细
export function getDriveSchool(id) {
  return request({
    url: '/admin/driveSchool/' + id,
    method: 'get'
  })
}

// 新增平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
export function addDriveSchool(data) {
  return request({
    url: '/admin/driveSchool',
    method: 'post',
    data: data
  })
}

// 修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
export function updateDriveSchool(data) {
  return request({
    url: '/admin/driveSchool',
    method: 'put',
    data: data
  })
}

// 删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
export function delDriveSchool(id) {
  return request({
    url: '/admin/driveSchool/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
export function changeStatus(data) {
  return request({
    url: '/admin/driveSchool/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
export function exportDriveSchool(query) {
  return request({
    url: '/admin/driveSchool/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}